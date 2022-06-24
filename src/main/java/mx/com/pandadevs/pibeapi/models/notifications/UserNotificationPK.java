package mx.com.pandadevs.pibeapi.models.notifications;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserNotificationPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "notification_id")
    private Integer  notificacitionId;

    @Column(name = "user_id")
    private Long  userId;

    public Integer getNotificacitionId() {
        return notificacitionId;
    }

    public void setNotificacitionId(Integer notificacitionId) {
        this.notificacitionId = notificacitionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }    

    

}
