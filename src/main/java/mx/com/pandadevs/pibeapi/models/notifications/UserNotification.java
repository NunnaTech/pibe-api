package mx.com.pandadevs.pibeapi.models.notifications;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;
import mx.com.pandadevs.pibeapi.models.users.User;
// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "USER_NOTIFICATIONS")
@Setter
@Getter
public class UserNotification extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private UserNotificationPK id;

    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "text")
    private String action;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean seen;

    // Relationship
    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "notification_id", insertable = false, updatable = false)
    private Notification notification;

}
