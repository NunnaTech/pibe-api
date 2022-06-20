package mx.com.pandadevs.pibeapi.models.contacts;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

// Lombok
import lombok.Getter;
import lombok.Setter;

// Models
import mx.com.pandadevs.pibeapi.utils.PibeModel;

@Entity
@Table(name = "CONTACTS")
@Setter
@Getter
public class Contact extends PibeModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ContactFk id;
}
