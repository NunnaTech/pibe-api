package mx.com.pandadevs.pibeapi.models.auth.common;

public class AuthRequest {
    private String username;
    private String password;
    private String currentPassword;

    public String getUsername() {
        return username;
    }

    public void setEmail(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
