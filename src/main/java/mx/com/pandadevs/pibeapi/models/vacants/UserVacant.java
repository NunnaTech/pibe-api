package mx.com.pandadevs.pibeapi.models.vacants;
// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.users.User;

@Entity
@Table(name = "USERS_VACANTS")
@Setter
@Getter
public class UserVacant extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_vacant")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vacant_id", insertable = false, updatable = false)
    private Vacant vacant;

    @ManyToOne
    @JoinColumn(name = "process_id", insertable = false, updatable = false)
    private Process process;
}
