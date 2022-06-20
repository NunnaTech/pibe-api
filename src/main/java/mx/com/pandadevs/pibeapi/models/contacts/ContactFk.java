package mx.com.pandadevs.pibeapi.models.contacts;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;

// Lombok
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter

public class ContactFk implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long  userId;

    @Column(name = "contact_id")
    private Long  contact_id;    
}
