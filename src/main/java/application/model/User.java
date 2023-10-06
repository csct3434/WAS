package application.model;

public class User {
    private String userId;
    private String password;
    private String username;
    private String email;

    public User(String userId, String password, String username, String email) {
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

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + username + ", email=" + email + "]";
    }
}
