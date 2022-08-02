package mx.com.pandadevs.pibeapi.models.roles.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RoleDto {

    @NotNull
    @NotBlank
    private String authority;
    @NotNull
    private String description;

    // Getters & Setters

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
