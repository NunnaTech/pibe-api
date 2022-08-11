package mx.com.pandadevs.pibeapi.models.work_experiences.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class WorkExperienceDto {
    private Integer id;
    @NotNull
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    @NotEmpty(message = "Please provide a position work")
    @Size(
            min = 5,
            max = 50)
    private String position;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startPeriod;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endPeriod;
    @NotNull
    @Pattern(regexp = "[A-Za-zÀ-ÿ '-.0-9]*")
    @NotEmpty(message = "Please provide a short description")
    @Size(
            min = 5,
            max = 255)
    private String activities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDateTime startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDateTime endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
}
