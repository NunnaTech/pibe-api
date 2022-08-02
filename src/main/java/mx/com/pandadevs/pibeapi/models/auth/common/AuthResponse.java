package mx.com.pandadevs.pibeapi.models.auth.common;

import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;

import java.util.Optional;

public class AuthResponse {

    private Optional<UserProfileDto> user;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(Optional<UserProfileDto> user, String token) {
        this.user = user;
        this.token = token;
    }

    public Optional<UserProfileDto> getUser() {
        return user;
    }

    public void setUser(Optional<UserProfileDto> user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
