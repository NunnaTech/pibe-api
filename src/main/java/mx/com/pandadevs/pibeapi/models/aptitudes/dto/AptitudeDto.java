package mx.com.pandadevs.pibeapi.models.aptitudes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AptitudeDto {
    private Integer id;
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 40)
    private String name;


    @NotNull
    private Boolean active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
