package mx.com.pandadevs.pibeapi.models.notifications;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;

// Lombok
import lombok.Getter;
import lombok.Setter;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Embeddable
@Setter
@Getter
public class UserNotificationPK extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "notification_id")
    private Integer  notificacitionId;

    @Column(name = "user_id")
    private Long  userId;    

}
