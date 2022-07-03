package mx.com.pandadevs.pibeapi.models.work_experiences.dto;

import java.time.LocalDateTime;

public class WorkExperienceDto {
    private String position;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private String activities;

    // Getters && Setters

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
