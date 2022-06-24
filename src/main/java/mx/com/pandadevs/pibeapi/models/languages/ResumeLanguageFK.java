package mx.com.pandadevs.pibeapi.models.languages;
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
    private Long  lenguageId;

    // Getters & Setters
    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Long getLenguageId() {
        return lenguageId;
    }

    public void setLenguageId(Long lenguageId) {
        this.lenguageId = lenguageId;
    }    

    
}
