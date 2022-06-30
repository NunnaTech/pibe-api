package mx.com.pandadevs.pibeapi.models.contacts.entity;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "CONTACTS")
public class Contact extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ContactFk id;

    // Getters & Setters
    
    public ContactFk getId() {
        return id;
    }

    public void setId(ContactFk id) {
        this.id = id;
    }
    
}
