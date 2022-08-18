package mx.com.pandadevs.pibeapi.models.notifications.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "USERS_NOTIFICATIONS")
public class UserNotification extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private UserNotificationPK id;

    @Column(
        nullable = false,
        columnDefinition = "TEXT")
    private String action;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 0")
    private Boolean seen;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id" )
    private User user;

    @ManyToOne
    @JoinColumn(name = "notification_id", insertable = false, updatable = false)
    private Notification notification;

    public UserNotification() {
    }

    public UserNotification(UserNotificationPK id, String action, Boolean seen) {
        this.id = id;
        this.action = action;
        this.seen = seen;
    }

    public UserNotification(String action, Boolean seen, User user, Notification notification) {
        this.action = action;
        this.seen = seen;
        this.user = user;
        this.notification = notification;
    }

    // Getters & Setters
    public UserNotificationPK getId() {
        return id;
    }

    public void setId(UserNotificationPK id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    

}
