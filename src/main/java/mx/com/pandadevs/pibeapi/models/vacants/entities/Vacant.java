package mx.com.pandadevs.pibeapi.models.vacants.entities;
// Java
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

// Lombok
import com.fasterxml.jackson.annotation.*;

// Models
import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
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
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @Column(
        name = "public",
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean isPublic;

    @Column(columnDefinition = "varchar(200)")
    private String image;

    @ManyToOne
    @JoinColumn(name = "schedule_id", insertable = false, updatable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private RepublicState state;

    @ManyToOne
    @JoinColumn(name = "period_id", insertable = false, updatable = false)
    private Period period;

    @ManyToOne
    @JoinColumn(name = "mode_id", insertable = false, updatable = false)
    private Mode mode;

    @ManyToOne
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private User user;

    // Relationships
    
    // VACANTS FAVORITES
    @ManyToMany(mappedBy = "favoitesVacants")
    private List<User> users = new ArrayList<>();

    // VACANTS BENEFITS
     @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "VACANTS_BENEFITS",
            joinColumns = @JoinColumn(name = "benefit_id"),
            inverseJoinColumns = @JoinColumn(name = "vacant_id"))
    private List<Benefit> benefits;

    public void addToFavorite(Benefit benefit) {
        benefits.add(benefit);
        benefit.getVacants().add(this);
    }

    public void removeFromFavorite(Benefit benefit) {
        benefits.remove(benefit);
        benefit.getVacants().remove(this);
    }

    // User Vacants
    @OneToMany(mappedBy = "vacant", cascade = {CascadeType.ALL})
    private Set<UserVacant> userVacants;

    // Getters && Setters

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
