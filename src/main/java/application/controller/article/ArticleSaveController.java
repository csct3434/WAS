package application.controller.article;

import application.controller.Controller;
import application.dto.article.ArticleSaveDto;
import application.service.ArticleService;
import application.service.SessionService;
import view.ModelAndView;
import webserver.exceptions.UnauthorizedException;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.Location;

import java.io.IOException;

public class ArticleSaveController implements Controller {
    private final ArticleService articleService = ArticleService.getInstance();
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        try {
            String sessionId = httpRequest.getSessionId();
            String userId = sessionService.findUserId(sessionId);

            checkLoginStatus(sessionId);

            ArticleSaveDto articleSaveDto = new ArticleSaveDto(
                    userId,
                    httpRequest.getParameter("title"),
                    httpRequest.getParameter("contents"));

            articleService.add(articleSaveDto);
            httpResponse.sendRedirect(Location.INDEX_PAGE);
        } catch (UnauthorizedException e) {
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED);
            ModelAndView modelAndView = new ModelAndView(Location.ERROR_PAGE);
            modelAndView.addAttribute("errorMessage", "401 UNAUTHORIZED");
            return modelAndView;
        }

        return null;
    }

    private void checkLoginStatus(String sessionId) throws UnauthorizedException {
        if (!sessionService.verifySessionId(sessionId)) {
            throw new UnauthorizedException();
        }
    }
}
