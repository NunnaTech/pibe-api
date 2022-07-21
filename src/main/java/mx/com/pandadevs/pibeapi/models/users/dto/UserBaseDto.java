package mx.com.pandadevs.pibeapi.models.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserBaseDto {
    private Long id;
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 50)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 50)
    private String username;


    @JsonIgnore
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 100)
    private String password;

    private Boolean active;

    public UserBaseDto(UserBaseDto baseDTO) {
        this.id = baseDTO.getId();
        this.email = baseDTO.getEmail();
        this.username = baseDTO.getUsername();
        this.password = baseDTO.getPassword();
        this.active = baseDTO.active;
    }

    public UserBaseDto() {
    }


    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBaseDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}
