package mx.com.pandadevs.pibeapi.models.states;

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
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.vacants.Vacant;


@Entity
@Table(name = "REPUBLIC_STATE")
@Setter
@Getter
public class RepublicState extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_republic_state")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;

    // Relationships
    
    // Vacants
    @OneToMany(mappedBy = "state", cascade = {CascadeType.ALL})
    private Set<Vacant> vacants;
    
    // Vacants
    @OneToMany(mappedBy = "state", cascade = {CascadeType.ALL})
    private Set<Profile> profiles;
    

}
