package mx.com.pandadevs.pibeapi.models.styles;
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

// Lombok
import lombok.Getter;
import lombok.Setter;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;


@Entity
@Table(name = "STYLES")
@Setter
@Getter
public class Style extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_style")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

     // Relationships
    
    // Resume
    @OneToMany(mappedBy = "style", cascade = {CascadeType.ALL})
    private Set<Resume> resumes;

}
