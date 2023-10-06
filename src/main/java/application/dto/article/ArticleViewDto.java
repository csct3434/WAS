package application.dto.article;

public class ArticleViewDto {
    private final String title;
    private final String username;
    private final String contents;
    private final String createDate;

    public ArticleViewDto(String title, String username, String contents, String createDate) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getContents() {
        return contents;
    }

    public String getCreateDate() {
        return createDate;
    }
}
