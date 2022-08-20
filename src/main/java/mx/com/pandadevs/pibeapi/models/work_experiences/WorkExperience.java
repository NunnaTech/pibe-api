package mx.com.pandadevs.pibeapi.models.work_experiences;
// Java
import java.io.Serializable;
import java.time.LocalDateTime;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Json
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

@Entity
@Table(name = "WORK_EXPERIENCES")
public class WorkExperience extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_experience")
    private Integer id;
    
    @Column(
        columnDefinition = "varchar(50)")
    private String position;
    
    @Column(
        name = "start_period",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime startPeriod;

    @Column(
        name = "end_period",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime endPeriod;
   
    @Column(
        columnDefinition = "TEXT")
    private String activities;
    
    @Column(
        columnDefinition = "tinyint default 1")
    private Boolean active;

    // Relationship
    @ManyToOne
    @JoinColumn(name = "resume_id" )
    private Resume resume;

    // Getters && Setters

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
