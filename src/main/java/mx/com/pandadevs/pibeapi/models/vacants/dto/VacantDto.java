package mx.com.pandadevs.pibeapi.models.vacants.dto;

import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class VacantDto {

    private Integer id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String salary;
    private Boolean isPublic;
    private String image;
    private ScheduleDto schedule;
    private PeriodDto period;
    private ModeDto mode;

    private List<BenefitDto> benefits;
    private UserDto creator;

    // Getters && Setters


    public List<BenefitDto> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<BenefitDto> benefits) {
        this.benefits = benefits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ScheduleDto getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleDto schedule) {
        this.schedule = schedule;
    }

    public PeriodDto getPeriod() {
        return period;
    }

    public void setPeriod(PeriodDto period) {
        this.period = period;
    }

    public ModeDto getMode() {
        return mode;
    }

    public void setMode(ModeDto mode) {
        this.mode = mode;
    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }
}
