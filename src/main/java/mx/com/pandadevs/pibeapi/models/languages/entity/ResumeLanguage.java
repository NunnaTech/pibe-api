package mx.com.pandadevs.pibeapi.models.languages.entity;
// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;



@Entity
@Table(name = "RESUME_LANGUAGES")
@IdClass(value = ResumeLanguageFK.class)
public class ResumeLanguage extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "resume_id")
    private Integer  resumeId;
    @Id
    @Column(name = "language_id")
    private Long  languageId;
    
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

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
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
