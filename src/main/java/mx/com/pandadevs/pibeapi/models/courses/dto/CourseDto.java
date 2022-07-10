package mx.com.pandadevs.pibeapi.models.courses.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CourseDto {

    private Integer id;
    @NotNull
    @NotBlank
    @Size(
            min = 5,
            max = 40)
    private String name;

    private Integer hours;

    private String trainingInstitution;

    private LocalDateTime realizationDate;

    private LocalDateTime finishedDate;

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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getTrainingInstitution() {
        return trainingInstitution;
    }

    public void setTrainingInstitution(String trainingInstitution) {
        this.trainingInstitution = trainingInstitution;
    }

    public LocalDateTime getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(LocalDateTime realizationDate) {
        this.realizationDate = realizationDate;
    }

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(LocalDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }
}
