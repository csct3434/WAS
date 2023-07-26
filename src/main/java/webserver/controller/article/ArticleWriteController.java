package webserver.controller.article;

import db.SessionDatabase;
import webserver.controller.Controller;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.utils.CookieConstants;
import webserver.utils.Location;

import java.io.IOException;

public class ArticleWriteController implements Controller {
    @Override
    public void process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String sessionId = httpRequest.getCookie().get(CookieConstants.SESSION_ID);

        if (isLoginStatus(sessionId)) {
            httpResponse.sendRedirect(Location.ARTICLE_WRITE_PAGE);
            return;
        }
        httpResponse.sendRedirect(Location.LOGIN_PAGE);
    }

    private boolean isLoginStatus(String sessionId) {
        return SessionDatabase.verifySessionId(sessionId);
    }
}
