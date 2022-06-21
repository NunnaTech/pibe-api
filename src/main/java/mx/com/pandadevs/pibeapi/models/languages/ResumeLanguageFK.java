package mx.com.pandadevs.pibeapi.models.languages;
// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;

// Lombok
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class ResumeLanguageFK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "resume_id")
    private Integer  resumeId;

    @Column(name = "language_id")
    private Long  lenguageId;    

}
