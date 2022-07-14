package mx.com.pandadevs.pibeapi.models.languages.entity;
// Java
import java.io.Serializable;

// Persistence
import javax.persistence.*;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;



@Entity
@Table(name = "RESUME_LANGUAGES")
public class ResumeLanguage extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer  id;
    
    @Column(
        name="level",
        nullable = false,
        length = 10 
        )
//    @Enumerated(value = EnumType.STRING)
    private String level;

    // Resume
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    // Lenguage
    @ManyToOne
    @JoinColumn(name = "language_id" )
    private Language language;

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
