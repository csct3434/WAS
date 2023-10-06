package application.service;

import application.dto.article.ArticleListDto;
import application.dto.article.ArticleSaveDto;
import application.dto.article.ArticleViewDto;
import db.ArticleDatabase;
import application.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private static ArticleService instance;
    private final UserService userService = UserService.getInstance();

    private ArticleService() {
    }

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    public void add(ArticleSaveDto dto) {
        String username = userService.findNameById(dto.getUserId());

        Article article = new Article(dto.getUserId(), username, dto.getTitle(), dto.getContents());

        ArticleDatabase.save(article);
    }

    public ArticleViewDto getViewDto(int articleId) {
        Article article = ArticleDatabase.findById(articleId);
        return new ArticleViewDto(article.getTitle(), article.getUsername(), article.getContents(), article.getCreateDate().toString());
    }

    public List<ArticleListDto> getList() {
        ArrayList<ArticleListDto> articleList = new ArrayList<>();

        for (Article article : ArticleDatabase.findAll()) {
            articleList.add(new ArticleListDto(
                    article.getCreateDate(),
                    article.getUsername(),
                    article.getArticleId(),
                    article.getTitle()));
        }

        return articleList;
    }
}
