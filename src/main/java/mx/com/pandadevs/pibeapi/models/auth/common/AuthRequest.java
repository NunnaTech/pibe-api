package mx.com.pandadevs.pibeapi.models.auth.common;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuthRequest {
    @Size(min = 5, max = 45,message = "Username must be a minimum of 5 characters and a maximum of 45 characters")
    private String username;
    private String password;
    private String currentPassword;
    private  String newPassword;
    @Pattern(regexp = "^[0-9]*$", message = "Special characters are not allowed")
    private int roleId;
    private String key;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
