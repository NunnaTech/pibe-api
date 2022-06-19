package mx.com.pandadevs.pibeapi.models.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class USerDTO {
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
        max = 100)
    private String password;

    private Boolean active;
}
