package mx.com.pandadevs.pibeapi.models.courses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CourseDto {

    private Integer id;
    @NotNull
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    @NotEmpty(message = "Please provide a course name")
    @Size(
            min = 5,
            max = 40)
    private String name;

    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=100, message="must be equal or less than 100")
    @NotEmpty(message = "Please provide a number of hours")
    private Integer hours;
    @NotNull
    private Boolean active;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    @NotEmpty(message = "Please provide a name of the training institution")
    @Size(
            min = 5,
            max = 40)
    private String trainingInstitution;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime realizationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime finishedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
