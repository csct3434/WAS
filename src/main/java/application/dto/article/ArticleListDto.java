package application.dto.article;

public class ArticleListDto {
    private final String createDate;
    private final String username;
    private final int articleId;
    private final String title;

    public ArticleListDto(String createDate, String username, int articleId, String title) {
        this.createDate = createDate;
        this.username = username;
        this.articleId = articleId;
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getUsername() {
        return username;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }
}
