package application.dto.article;

public class ArticleSaveDto {
    private final String userId;
    private final String title;
    private final String contents;

    public ArticleSaveDto(String userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
