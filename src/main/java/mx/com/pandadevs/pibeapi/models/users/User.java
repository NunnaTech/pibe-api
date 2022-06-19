package mx.com.pandadevs.pibeapi.models.users;

// Java
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// Persistence
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;


// Lombok
import lombok.Getter;
import lombok.Setter;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.notifications.UserNotification;
import mx.com.pandadevs.pibeapi.models.roles.Role;

@Entity
@Table(name = "USERS")
@Setter
@Getter
public class User extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    
    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(50)")
    private String email;

    @Column(
        nullable = false,
        columnDefinition = "varchar(100)")
    private String password;

    @Column(
        nullable = false,
        columnDefinition = "tinyint default 1")
    private Boolean active;

    @Column(
        name = "link_restore_password",
        unique = true, columnDefinition = "varchar(150)")
    private String linkRestorePassword;

    @Column(
        name = "link_activate_email",
        unique = true, columnDefinition = "varchar(150)")
    private String linkActivateEmail;

    // Relationships

    // Roles
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLES",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Role> roles;

    public void addRole() {
        roles = new HashSet<>();
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    // Notifications
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<UserNotification> notifications;

}
