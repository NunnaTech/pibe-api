package mx.com.pandadevs.pibeapi.models.users;

// Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Persistence
import javax.persistence.*;


// Models
import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import mx.com.pandadevs.pibeapi.utils.PibeModel;
import mx.com.pandadevs.pibeapi.models.logs.entities.Log;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotification;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.roles.Role;
import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;

@Entity
@Table(name = "USERS")
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

    public User() {}

    @Column(
        unique = true,
        nullable = false,
        columnDefinition = "varchar(50)")
    private String username;

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USERS_ROLES",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public void addRole() {
        roles = new ArrayList<>();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    // Contacts
    @OneToMany(mappedBy = "contact", cascade = {CascadeType.ALL})
    private List<Contact> contacts;

    // Notifications
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<UserNotification> notifications;

    // Profile
    @OneToOne(cascade = {CascadeType.ALL},mappedBy="user")

    private Profile profile;

    // Vacants
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Vacant> vacants;

    // vacants favorites
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "VACANTS_FAVORITES",
            joinColumns = @JoinColumn(name = "vacant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Vacant> favoitesVacants;

    public void addToFavorite(Vacant vacant) {
        favoitesVacants.add(vacant);
        vacant.getUsers().add(this);
    }

    public void removeFromFavorite(Vacant vacant) {
        favoitesVacants.remove(vacant);
        vacant.getUsers().remove(this);
    }

    // User Vacants
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<UserVacant> userVacants;

    // Logs

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Log> logs;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLinkRestorePassword() {
        return linkRestorePassword;
    }

    public void setLinkRestorePassword(String linkRestorePassword) {
        this.linkRestorePassword = linkRestorePassword;
    }

    public String getLinkActivateEmail() {
        return linkActivateEmail;
    }

    public void setLinkActivateEmail(String linkActivateEmail) {
        this.linkActivateEmail = linkActivateEmail;
    }

    public List<Role> getRoles() {
        return (List<Role>) roles;
    }

    public List<UserNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<UserNotification> notifications) {
        this.notifications = notifications;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Vacant> getVacants() {
        return vacants;
    }

    public void setVacants(List<Vacant> vacants) {
        this.vacants = vacants;
    }

    public List<Vacant> getFavoitesVacants() {
        return favoitesVacants;
    }

    public void setFavoitesVacants(List<Vacant> favoitesVacants) {
        this.favoitesVacants = favoitesVacants;
    }

    public List<UserVacant> getUserVacants() {
        return userVacants;
    }

    public void setUserVacants(List<UserVacant> userVacants) {
        this.userVacants = userVacants;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
