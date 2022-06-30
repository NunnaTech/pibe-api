package mx.com.pandadevs.pibeapi.models.languages.entity;
// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.utils.enums.Level;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

@Entity
@Table(name = "RESUME_LANGUAGES")
public class ResumeLanguage extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ResumeLanguageFK id;
    
    @Column(
        name="level",
        nullable = false,
        length = 10 
        )
//    @Enumerated(value = EnumType.STRING)
    private String level;

    // Resume
    @ManyToOne
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;

    // Lenguage
    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Language language;

    // Getters & Setters
    public ResumeLanguageFK getId() {
        return id;
    }

    public void setId(ResumeLanguageFK id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    
}
