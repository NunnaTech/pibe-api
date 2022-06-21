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

// Lombok
import lombok.Getter;
import lombok.Setter;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

@Entity
@Table(name = "COURSES")
@Setter
@Getter
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
    private String trainingIstitution;
    
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

    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;
}
