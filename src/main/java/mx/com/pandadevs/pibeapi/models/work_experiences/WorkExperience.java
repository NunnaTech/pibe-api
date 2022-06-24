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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_work_experience")
@Table(name = "WORK_EXPERIENCES")
public class WorkExperience extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_experience")
    private Integer id;
    
    @Column(
        nullable = false,
        columnDefinition = "varchar(50)")
    private String position;
    
    @Column(
        name = "start_period",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime startPeriode;

    @Column(
        name = "end_period",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime endPeriode;
   
    @Column(
        nullable = false,
        columnDefinition = "TEXT")
    private String activities;
    
    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;
}
