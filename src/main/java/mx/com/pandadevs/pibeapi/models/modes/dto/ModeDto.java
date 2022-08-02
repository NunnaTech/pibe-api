package mx.com.pandadevs.pibeapi.models.modes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModeDto {

    private Integer id;
    @NotNull(message = "Please provide a name")
    @NotBlank(message = "Please provide a name")
    @Size(min = 5, max = 40, message = "Please provide a name with 5 - 40 characters")
    private String name;

    public ModeDto() {
    }

    public ModeDto(String name) {
        this.name = name;
    }

    public ModeDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
