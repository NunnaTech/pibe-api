package mx.com.pandadevs.pibeapi.models.contacts.entity;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.*;

// Models
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "CONTACTS")
public class Contact extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ContactFk id;
    // Relationship
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("contact_id")
    @JoinColumn(name = "contact_id", insertable = false, updatable = false)
    private User contact;

    // Getters & Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getContact() {
        return contact;
    }

    public void setContact(User contact) {
        this.contact = contact;
    }

    public ContactFk getId() {
        return id;
    }

    public void setId(ContactFk id) {
        this.id = id;
    }
    
}
