package mx.com.pandadevs.pibeapi.models.users.dto;

import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.roles.dto.RoleDto;

import java.util.List;

public class UserProfileDto extends UserBaseDto {
    private ProfileDto profile;
    private List<RoleDto> authorities;

    public ProfileDto getProfile() {
        return profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public List<RoleDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleDto> authorities) {
        this.authorities = authorities;
    }
}
