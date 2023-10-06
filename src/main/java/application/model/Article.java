package application.model;

import java.time.LocalDate;

public class Article {
    private static int articleIndex = 1;

    private final int articleId;
    private final String userId;
    private final String username;
    private final String createDate;
    private String title;
    private String contents;

    public Article(String userId, String username, String title, String contents) {
        this.articleId = articleIndex++;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDate.now().toString();
    }

    public int getArticleId() {
        return articleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getCreateDate() {
        return createDate;
    }
}
