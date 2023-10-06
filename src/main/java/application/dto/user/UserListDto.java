package application.dto.user;

public class UserListDto {
    private final String userId;
    private final String username;
    private final String email;

    public UserListDto(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
