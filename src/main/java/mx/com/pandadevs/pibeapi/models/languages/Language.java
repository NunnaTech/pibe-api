package mx.com.pandadevs.pibeapi.models.languages;

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
import javax.persistence.CascadeType;


// Models
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;
    
    // Relationship

    // Resume Lenguage
    @JsonIgnore
    @OneToMany(mappedBy = "lenguage")
    private Set<ResumeLanguage> resumes;

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

    public Set<ResumeLanguage> getResumes() {
        return resumes;
    }

    public void setResumes(Set<ResumeLanguage> resumes) {
        this.resumes = resumes;
    }


}
