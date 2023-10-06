package application.controller.user;

import application.controller.Controller;
import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.io.IOException;

public class UserLogoutController implements Controller {
    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String sessionId = httpRequest.getSessionId();
        httpResponse.setCookie("sid", sessionId);
        httpResponse.setCookie("Path", "/");
        httpResponse.setCookie("Max-Age", "0");

        httpResponse.sendRedirect("/index.html");
        return null;
    }
}
