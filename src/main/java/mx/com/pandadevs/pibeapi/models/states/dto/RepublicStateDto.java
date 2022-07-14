package mx.com.pandadevs.pibeapi.models.states.dto;

import org.hibernate.validator.constraints.UniqueElements;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RepublicStateDto {
    private Integer id;

    @NotNull
    @NotBlank
    @Size(
            min = 2,
            max = 40)
    private String name;

    // Getters & Setters
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
