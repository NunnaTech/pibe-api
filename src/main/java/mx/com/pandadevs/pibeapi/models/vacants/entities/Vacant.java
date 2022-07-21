package mx.com.pandadevs.pibeapi.models.vacants.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.benefits.Benefit;
import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.periods.Period;
import mx.com.pandadevs.pibeapi.models.schedule.Schedule;
import mx.com.pandadevs.pibeapi.models.states.RepublicState;
import mx.com.pandadevs.pibeapi.models.users.User;

@Entity
@Table(name = "VACANTS")
public class Vacant extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacant")
    private Integer id;

    @Column(
            nullable = false,
            columnDefinition = "varchar(40)")
    private String title;
    @Column(
            nullable = false,
            columnDefinition = "TEXT")
    private String description;

    @Column(
            name = "start_date",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime startDate;

    @Column(
            name = "end_date",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime endDate;
    @Column(
            nullable = false,
            columnDefinition = "varchar(80)")
    private String salary;

    @Column(
            columnDefinition = "tinyint default 1")
    private Boolean active = true;

    @Column(
            name = "public",
            columnDefinition = "tinyint default 0")
    private Boolean isPublic = false;

    @Column(columnDefinition = "varchar(200)")
    private String image;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private RepublicState state;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "mode_id")
    private Mode mode;

    @ManyToOne()
    @JoinColumn(name = "created_by")
    private User user;

    @ManyToMany(mappedBy = "favoitesVacants")
    private List<User> users = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "VACANTS_BENEFITS",
            joinColumns = @JoinColumn(name = "benefit_id"),
            inverseJoinColumns = @JoinColumn(name = "vacant_id"))
    private List<Benefit> benefits;

    public Vacant() {
    }

    public void addToFavorite(Benefit benefit) {
        benefits.add(benefit);
        benefit.getVacants().add(this);
    }

    public void removeFromFavorite(Benefit benefit) {
        benefits.remove(benefit);
        benefit.getVacants().remove(this);
    }

    @OneToMany(mappedBy = "vacant", cascade = {CascadeType.ALL})
    private Set<UserVacant> userVacants;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public RepublicState getState() {
        return state;
    }

    public void setState(RepublicState state) {
        this.state = state;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public Set<UserVacant> getUserVacants() {
        return userVacants;
    }

    public void setUserVacants(Set<UserVacant> userVacants) {
        this.userVacants = userVacants;
    }
}
