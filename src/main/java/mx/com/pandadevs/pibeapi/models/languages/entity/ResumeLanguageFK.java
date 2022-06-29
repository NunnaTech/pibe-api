package mx.com.pandadevs.pibeapi.models.languages.entity;
// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ResumeLanguageFK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "resume_id")
    private Integer  resumeId;

    @Column(name = "language_id")
    private Long  languageId;

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

    
}
