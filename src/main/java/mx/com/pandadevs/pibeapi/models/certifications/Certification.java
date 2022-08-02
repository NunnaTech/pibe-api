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

// Models
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

@Entity
@Table(name = "CERTIFICATIONS")
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

    // Relationships
    @ManyToOne
    @JoinColumn(name = "resume_id" )
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDateTime getObtainedDate() {
        return obtainedDate;
    }

    public void setObtainedDate(LocalDateTime obtainedDate) {
        this.obtainedDate = obtainedDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
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
