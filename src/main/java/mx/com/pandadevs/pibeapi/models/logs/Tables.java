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


// Models
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "TABLES")
public class Tables extends PibeModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    private String name;

    // Logs
    @JsonIgnore
    @OneToMany(mappedBy = "table", cascade = {CascadeType.ALL})
    private Set<Log> logs;

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    
}
