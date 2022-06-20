package mx.com.pandadevs.pibeapi.models.logs;
// Java
import java.io.Serializable;
import java.util.Set;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import mx.com.pandadevs.pibeapi.utils.enums.Action;
import mx.com.pandadevs.pibeapi.models.users.User;


@Entity
@Table(name = "LOGS")
@Setter
@Getter
public class Log extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;
    
    @Column(
        name = "old_data",
        columnDefinition = "json")
    private String oldData;

    @Column(
        name = "new_data",
        nullable = false,
        columnDefinition = "json")
    private String newData;

    @Column(
        name="action",
        nullable = false,
        length = 12 
        )
    @Enumerated(value = EnumType.STRING)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id", insertable = false, updatable = false)
    private Tables table;
}
