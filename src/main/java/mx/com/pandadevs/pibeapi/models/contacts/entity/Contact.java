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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private User contact;

    public Contact() {
    }

    public Contact( User contact,User user) {
        this.user = user;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", user=" + user +
                ", contact=" + contact +
                '}';
    }
}
