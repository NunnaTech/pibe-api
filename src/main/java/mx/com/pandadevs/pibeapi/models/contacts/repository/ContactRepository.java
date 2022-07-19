package mx.com.pandadevs.pibeapi.models.contacts.repository;

import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT * FROM contacts WHERE user_id = :id", nativeQuery = true)
    List<Contact> findContactsByUsername(Long id);

    List<Contact> findContactByContactUsername(String username);

    Contact findContactById(long id);

    @Query(value = "DELETE FROM contacts WHERE user_id = :userId AND contact_id = :contactId", nativeQuery = true)
    Boolean deleteContactByUserIdAndContactId(Long userId, Long contactId);

    void deleteById(Long id);
}
