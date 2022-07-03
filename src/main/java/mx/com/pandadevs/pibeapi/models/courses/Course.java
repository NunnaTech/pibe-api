package mx.com.pandadevs.pibeapi.models.courses;
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

// Models
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_course")
@Table(name = "COURSES")
public class Course extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Integer id;
    
    @Column(
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;
    
    @Column(
        nullable = false,
        columnDefinition = "int")
    private Integer hours;

    @Column(
        name = "training_institution",
        nullable = false,
        columnDefinition = "varchar(50)")
    private String trainingInstitution;
    
    @Column(
        name = "realization_date",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime realizationDate;

    @Column(
        name = "finished_date",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime finishedDate;
   
    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;

    // Getters & Setters
    
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
