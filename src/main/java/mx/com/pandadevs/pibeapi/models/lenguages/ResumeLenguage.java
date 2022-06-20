package mx.com.pandadevs.pibeapi.models.lenguages;
// Java
import java.io.Serializable;
import java.util.Set;

// Persistence
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

// Lombok
import lombok.Getter;
import lombok.Setter;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import mx.com.pandadevs.pibeapi.models.vacants.Vacant;
import mx.com.pandadevs.pibeapi.utils.Level;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "RESUME_LEGUAGES")
@Setter
@Getter
public class ResumeLenguage extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ResumeLenguageFK id;
    
    @Column(
        name="level",
        nullable = false,
        length = 10 
        )
    @Enumerated(value = EnumType.STRING)
    private Level level;

    // Relationship
    
    // User
    @ManyToOne
    @MapsId("resume_id")
    @JoinColumn(name = "resume_id", insertable = false, updatable = false)
    private Resume resume;

    // Notification
    @ManyToOne
    @JoinColumn(name = "language_id", insertable = false, updatable = false)
    private Lenguage lenguage;

}
