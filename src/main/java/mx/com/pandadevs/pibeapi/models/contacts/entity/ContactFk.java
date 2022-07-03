package mx.com.pandadevs.pibeapi.models.contacts.entity;

// Java
import java.io.Serializable;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable

public class ContactFk implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long  userId;

    @Column(name = "contact_id")
    private Long  contact_id;

    // Getters & Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContact_id() {
        return contact_id;
    }

    public void setContact_id(Long contact_id) {
        this.contact_id = contact_id;
    }    
    
}
