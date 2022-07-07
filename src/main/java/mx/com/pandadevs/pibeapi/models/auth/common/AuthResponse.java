package mx.com.pandadevs.pibeapi.models.auth.common;

import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.users.User;

import java.util.Optional;

public class AuthResponse {
    private User user;
    private Optional<Profile> profile;
    private String token;

    public AuthResponse(User user, Optional<Profile> profile, String token) {
        this.user = user;
        this.profile = profile;
        this.token = token;
    }

    public AuthResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Optional<Profile> getProfile() {
        return profile;
    }

    public void setProfile(Optional<Profile> profile) {
        this.profile = profile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
