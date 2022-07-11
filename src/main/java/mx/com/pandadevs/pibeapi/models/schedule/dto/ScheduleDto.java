package mx.com.pandadevs.pibeapi.models.schedule.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ScheduleDto {
    private Integer id;
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 40)
    private String name;

    public ScheduleDto() {
    }

    public ScheduleDto(Integer id, String name) {
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
