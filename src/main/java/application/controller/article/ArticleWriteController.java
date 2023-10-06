package application.controller.article;

import application.controller.Controller;
import application.service.SessionService;
import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.utils.CookieConstants;
import webserver.utils.Location;

import java.io.IOException;

public class ArticleWriteController implements Controller {
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String sessionId = httpRequest.getCookie(CookieConstants.SESSION_ID);

        if (sessionService.verifySessionId(sessionId)) {
            httpResponse.sendRedirect(Location.ARTICLE_WRITE_PAGE);
        } else {
            httpResponse.sendRedirect(Location.LOGIN_PAGE);
        }
        return null;
    }
}
