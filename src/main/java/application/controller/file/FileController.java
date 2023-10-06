package application.controller.file;

import application.controller.Controller;
import application.service.SessionService;
import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.FileUtils;

import java.io.IOException;

public class FileController implements Controller {
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String sessionId = httpRequest.getSessionId();
        String filePath = httpRequest.getPath();

        filePath = FileUtils.checkFilePath(filePath);

        ModelAndView modelAndView = new ModelAndView(filePath);

        if(sessionService.verifySessionId(sessionId)) {
            modelAndView.setLogin(sessionService.findUserId(sessionId));
        }

        httpResponse.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}
