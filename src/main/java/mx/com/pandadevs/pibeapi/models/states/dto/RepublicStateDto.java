package mx.com.pandadevs.pibeapi.models.states.dto;

import org.hibernate.validator.constraints.UniqueElements;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RepublicStateDto {
    private Integer id;
    @UniqueElements
    @NotNull(message = "Please provide a name")
    @NotBlank(message = "Please provide a name")
    @Size(min = 2, max = 40, message = "Please provide a name with 2 - 40 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
