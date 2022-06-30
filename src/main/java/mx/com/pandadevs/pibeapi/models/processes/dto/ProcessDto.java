package mx.com.pandadevs.pibeapi.models.processes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProcessDto {
    private Integer id;
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 40)
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