package mx.com.pandadevs.pibeapi.models.studies;
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
@Table(name = "STUDIES")
@Setter
@Getter
public class Study extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_study")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    @Column(
        name = "start_date",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime startPeriod;

    @Column(
        name = "end_date",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime endPeriod;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;
}
