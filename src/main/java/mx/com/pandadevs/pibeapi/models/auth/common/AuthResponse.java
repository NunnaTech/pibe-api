package mx.com.pandadevs.pibeapi.models.auth.common;

import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;

import java.util.Optional;

public class AuthResponse {

    private Optional<UserProfileDto> profile;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(Optional<UserProfileDto> profile, String token) {
        this.profile = profile;
        this.token = token;
    }

    public Optional<UserProfileDto> getProfile() {
        return profile;
    }

    public void setProfile(UserProfileDto profile) {
        this.profile = Optional.ofNullable(profile);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
