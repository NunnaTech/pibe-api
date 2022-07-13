package mx.com.pandadevs.pibeapi.models.languages.entity;
// Java
import java.io.Serializable;
import java.util.Objects;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;


@Embeddable
public class ResumeLanguageFK implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue
    @Column(name = "resume_id")
    private Integer  resumeId;
    @GeneratedValue
    @Column(name = "language_id")
    private Long  languageId;

    // methods
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.resumeId);
        hash = 59 * hash + Objects.hashCode(this.languageId);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResumeLanguageFK other = (ResumeLanguageFK) obj;
        if (!Objects.equals(this.languageId, other.languageId)) {
            return false;
        }
        if (!Objects.equals(this.resumeId, other.resumeId)) {
            return false;
        }
        return true;
    }
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
