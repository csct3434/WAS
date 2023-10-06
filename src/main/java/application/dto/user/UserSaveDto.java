package application.dto.user;

public class UserSaveDto {
    private final String userId;
    private final String password;
    private final String username;
    private final String email;

    public UserSaveDto(String userId, String password, String username, String email) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
