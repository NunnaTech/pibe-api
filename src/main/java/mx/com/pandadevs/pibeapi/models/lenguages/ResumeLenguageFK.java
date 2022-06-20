package mx.com.pandadevs.pibeapi.models.lenguages;
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
public class ResumeLenguageFK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "resume_id")
    private Integer  resumeId;

    @Column(name = "lenguage_id")
    private Long  lenguageId;    

}
