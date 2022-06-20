package mx.com.pandadevs.pibeapi.models.lenguages;

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
import mx.com.pandadevs.pibeapi.models.vacants.Vacant;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "LEGUAGES")
@Setter
@Getter
public class Lenguage extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lenguage")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(5)")
    private String abbreviation;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;
    
    // Relationship

    // Resume Lenguage
    @OneToMany(mappedBy = "lenguages", cascade = {CascadeType.ALL})
    private Set<ResumeLenguage> resumes;
}
