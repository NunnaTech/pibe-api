package mx.com.pandadevs.pibeapi.models.notifications.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NotificationDto {

    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 50)
    @Email
    private String type;

    private String description;

    // Getters & Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
