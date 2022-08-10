package mx.com.pandadevs.pibeapi.models.vacants.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class VacantDto {

    private Integer id;
    @NotNull(message = "Please provide a title")
    @NotBlank(message = "Please provide a title")
    @Size(min = 2, max = 40, message = "Please provide a title with 2 - 40 characters")
    private String title;
    @NotNull(message = "Please provide a description")
    @NotBlank(message = "Please provide a description")
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endDate;
    @NotNull(message = "Please provide a salary")
    @NotBlank(message = "Please provide a salary")
    @Size(min = 2, max = 40, message = "Please provide a salary with 2 - 80 characters")
    private String salary;
    private Boolean isPublic;
    private String image;
    private ScheduleDto schedule;
    private PeriodDto period;
    private ModeDto mode;
    private RepublicStateDto state;
    private List<BenefitDto> benefits;
    private UserDto creator;

    public List<BenefitDto> getBenefits() {
        return benefits;
    }

    public VacantDto() {
    }

    public VacantDto(String title, String description, LocalDateTime startDate, LocalDateTime endDate, String salary, Boolean isPublic, String image, ScheduleDto schedule, PeriodDto period, ModeDto mode, RepublicStateDto state, List<BenefitDto> benefits, UserDto creator) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
        this.isPublic = isPublic;
        this.image = image;
        this.schedule = schedule;
        this.period = period;
        this.mode = mode;
        this.state = state;
        this.benefits = benefits;
        this.creator = creator;
    }

    public VacantDto(Integer id, String title, String description, LocalDateTime startDate, LocalDateTime endDate, String salary, Boolean isPublic, String image, ScheduleDto schedule, PeriodDto period, ModeDto mode, RepublicStateDto state, List<BenefitDto> benefits, UserDto creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
        this.isPublic = isPublic;
        this.image = image;
        this.schedule = schedule;
        this.period = period;
        this.mode = mode;
        this.state = state;
        this.benefits = benefits;
        this.creator = creator;
    }

    public RepublicStateDto getState() {
        return state;
    }

    public void setState(RepublicStateDto state) {
        this.state = state;
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
