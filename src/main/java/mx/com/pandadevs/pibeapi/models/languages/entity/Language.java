package mx.com.pandadevs.pibeapi.models.languages.entity;

// Java
import java.io.Serializable;
import java.util.Set;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "LANGUAGES")
public class Language extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_language")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(5)")
    private String abbreviation;

    @Column(
        columnDefinition = "tinyint default 1")
    private Boolean active;
    
    // Relationship

    // Resume Lenguage
    @OneToMany(mappedBy = "language")
    private Set<ResumeLanguage> resumeLanguages;

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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<ResumeLanguage> getResumeLanguages() {
        return resumeLanguages;
    }

    public void setResumeLanguages(Set<ResumeLanguage> resumeLanguages) {
        this.resumeLanguages = resumeLanguages;
    }
}
