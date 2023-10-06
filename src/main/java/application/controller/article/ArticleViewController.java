package application.controller.article;

import application.controller.Controller;
import application.dto.article.ArticleViewDto;
import application.service.ArticleService;
import application.service.SessionService;
import view.ModelAndView;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatus;
import webserver.utils.Location;

import java.io.IOException;

public class ArticleViewController implements Controller {
    private final ArticleService articleService = ArticleService.getInstance();
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public ModelAndView process(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        String sessionId = httpRequest.getSessionId();

        if (sessionService.verifySessionId(sessionId)) {
            ModelAndView modelAndView = new ModelAndView(Location.ARTICLE_VIEW_PAGE);

            modelAndView.setLogin(sessionService.findUserId(sessionId));

            int articleId = Integer.parseInt(httpRequest.getParameter("articleId"));

            ArticleViewDto viewDto = articleService.getViewDto(articleId);
            modelAndView.addAttribute("username", viewDto.getUsername());
            modelAndView.addAttribute("contents", viewDto.getContents());
            modelAndView.addAttribute("title", viewDto.getTitle());
            modelAndView.addAttribute("createDate", viewDto.getCreateDate());

            httpResponse.setStatus(HttpStatus.OK);
            return modelAndView;
        }
        httpResponse.sendRedirect(Location.LOGIN_PAGE);
        return null;
    }
}
