package db;

import application.model.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ArticleDatabase {
    private static final ConcurrentHashMap<Integer, Article> articles = new ConcurrentHashMap<>();

    public static void save(Article article) {
        articles.put(article.getArticleId(), article);
    }

    public static Article findById(int articleId) {
        return articles.get(articleId);
    }

    public static List<Article> findAll() {
        ArrayList<Article> articleList = new ArrayList<>(articles.values());
        return Collections.unmodifiableList(articleList);
    }
}
