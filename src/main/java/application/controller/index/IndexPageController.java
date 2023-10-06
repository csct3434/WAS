package application.controller.index;

import application.controller.Controller;
import application.service.ArticleService;
import application.service.SessionService;
import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.Location;

import java.io.IOException;

public class IndexPageController implements Controller {
    private final ArticleService articleService = ArticleService.getInstance();
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        ModelAndView modelAndView = new ModelAndView(Location.INDEX_PAGE);
        String sessionId = httpRequest.getSessionId();

        if (sessionService.verifySessionId(sessionId)) {
            modelAndView.setLogin(sessionService.findUserId(sessionId));
        }

        modelAndView.addAttribute("articleList", articleService.getList());

        httpResponse.setStatus(HttpStatus.OK);

        return modelAndView;
    }
}
