package mx.com.pandadevs.pibeapi.models.contacts.entity;

// Java
import java.io.Serializable;
import java.util.Objects;

// Persistence
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ContactFk implements Serializable {

    private Long  userId;
    private Long  contactId;

    public ContactFk() {
    }

    public ContactFk(Long userId, Long contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactFk contactFk = (ContactFk) o;
        return Objects.equals(userId, contactFk.userId) && Objects.equals(contactId, contactFk.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contactId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
