package mx.com.pandadevs.pibeapi.models.styles.dto;
// Validations
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class StyleDto {

    private Integer id;
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    @NotEmpty(message = "Please provide a style name")
    @Size(min = 2, max = 40)
    private String name;
    // Getters && Setters

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
