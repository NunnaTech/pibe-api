package mx.com.pandadevs.pibeapi.models.studies.dto;

import java.time.LocalDateTime;

public class StudyDto {

    private String name;
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    // Getters && Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
