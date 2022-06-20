package mx.com.pandadevs.pibeapi.models.certifications;

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
@Table(name = "CERTIFICATIONS")
@Setter
@Getter
public class Certification extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centification")
    private Integer id;
    
    @Column(
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;
    

    @Column(
        nullable = false,
        columnDefinition = "varchar(40)")
    private String company;

    @Column(
        name = "obtained_date",
        nullable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime obtainedDate;

    @Column(
        name = "expiration_date",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime expirationDate;
    
    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;
}
