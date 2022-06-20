package mx.com.pandadevs.pibeapi.models.logs;
// Java
import java.io.Serializable;
import java.util.Set;

// Persistence
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "TABLES")
@Setter
@Getter
public class Tables extends PibeModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    private String name;

    // Logs
    @OneToMany(mappedBy = "table", cascade = {CascadeType.ALL})
    private Set<Log> logs;
}
