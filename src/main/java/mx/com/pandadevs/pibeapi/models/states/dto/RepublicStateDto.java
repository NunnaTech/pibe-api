package mx.com.pandadevs.pibeapi.models.states.dto;

import org.hibernate.validator.constraints.UniqueElements;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RepublicStateDto {
    @NotNull
    @UniqueElements
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
