package webserver;

import application.controller.Controller;
import application.controller.ControllerResolver;
import view.ModelAndView;
import view.DynamicViewRender;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.utils.ContentTypeResolver;
import webserver.utils.HttpField;

import java.io.IOException;

public class DispatcherServlet {
    private static DispatcherServlet instance;

    private static final ControllerResolver controllerResolver = ControllerResolver.getInstance();
    private static final DynamicViewRender DYNAMIC_VIEW_RENDER = DynamicViewRender.getInstance();

    private DispatcherServlet() {
    }

    public static DispatcherServlet getInstance() {
        if (instance == null) {
            instance = new DispatcherServlet();
        }
        return instance;
    }

    public void dispatch(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        Controller controller = resolveController(httpRequest);

        ModelAndView modelAndView = controller.process(httpRequest, httpResponse);

        if(modelAndView == null) {
            return;
        }

        byte[] bytes = DYNAMIC_VIEW_RENDER.render(modelAndView);

        httpResponse.set(HttpField.CONTENT_TYPE, ContentTypeResolver.getContentType(modelAndView.getViewName()));
        httpResponse.set(HttpField.CONTENT_LENGTH, bytes.length);
        httpResponse.setBody(bytes);
    }

    private Controller resolveController(HttpRequest httpRequest) {
        String path = httpRequest.getPath();
        String method = httpRequest.getMethod();
        return controllerResolver.resolve(path, method);
    }
}
