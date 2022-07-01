package mx.com.pandadevs.pibeapi.models.contacts.repository;

import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import mx.com.pandadevs.pibeapi.models.contacts.entity.ContactFk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact, ContactFk> {
    List<Contact> findContactByContactUsername(String username);
}
