package mx.com.pandadevs.pibeapi.models.roles;
// Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Persistence
import javax.persistence.*;

// Models
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "ROLES")
public class Role extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(40)")
    private String name;
    
    @Column(
        columnDefinition = "varchar(40)")
    private String description;

    @Column(
        columnDefinition = "tinyint default 1")
    private Boolean active;

    // Relationships

    // VACANTS FAVORITES
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
}
