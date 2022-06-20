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

// Lombok
import lombok.Getter;
import lombok.Setter;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "WORK_EXPERIENCES")
@Setter
@Getter
public class WorkExperience extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_experiences")
    private Integer id;
    
    @Column(
        nullable = false,
        columnDefinition = "varchar(50)")
    private String position;
    
    @Column(
        name = "start_periode",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime startPeriode;

    @Column(
        name = "end_periode",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime endPeriode;
   
    @Column(
        nullable = false,
        columnDefinition = "text")
    private String activities;
    
    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;
}
