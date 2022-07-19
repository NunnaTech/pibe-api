package mx.com.pandadevs.pibeapi.models.contacts.service;

import mx.com.pandadevs.pibeapi.models.contacts.dto.ContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.dto.SingleContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import mx.com.pandadevs.pibeapi.models.contacts.entity.ContactFk;
import mx.com.pandadevs.pibeapi.models.contacts.mapper.ContactMapper;
import mx.com.pandadevs.pibeapi.models.contacts.repository.ContactRepository;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ContactService implements ServiceInterface<ContactFk,ContactDto> {
    private final ContactMapper mapper;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;
    public ContactService(ContactMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<ContactDto> getAll() {
        return null;
    }
    public Optional<List<SingleContactDto>> findContactsByUsername(String username){
        User user =  userRepository.findByUsername(username);
        if ( user == null) return Optional.empty();
        return  Optional.ofNullable(mapper.toSingleContactsDto(contactRepository.findContactsByUsername(user.getId())));
    }
    public List<ContactDto> getAllByUserid(String username) {
        return mapper.toContactsDto(contactRepository.findContactByContactUsername(username));
    }
    public Optional<ContactDto> findById(long id){
        return Optional.ofNullable(mapper.toContactDto(contactRepository.findContactById(id)));
    }

    @Override
    public Optional<ContactDto> getById(ContactFk id) {
        return Optional.empty();
    }

    @Override
    public ContactDto save(ContactDto entity) {
        return null;
    }

    public Optional<SingleContactDto> save(String myUsername, String contactUsername){
        Optional<User> userContacto = userRepository.findByUsernameAndActiveTrue(contactUsername);
        Optional<User> userMyInfo = userRepository.findByUsernameAndActiveTrue(myUsername);
        if (!userContacto.isPresent() || !userMyInfo.isPresent()) return Optional.empty();
        Contact contacto = new Contact(userContacto.get(), userMyInfo.get());
        List<Contact> myContacts = contactRepository.findContactsByUsername(userMyInfo.get().getId());

        //O(N)
        for (Contact item:  myContacts) {
            if(item.getContact().getId().equals(userContacto.get().getId())){
                return Optional.empty();
            }
        }
        contactRepository.save(contacto);
        return Optional.ofNullable(mapper.toSingleContactDto(contacto));
    }
    @Override
    public Optional<ContactDto> update(ContactDto entity) {
        return Optional.empty();
    }

    @Override
    public Optional<ContactDto> partialUpdate(ContactFk id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(ContactFk id) {
        return null;
    }

    public Boolean delete(String myUsername, String contactUsername) {
        Long id = 0l;
        Optional<User> userContacto = userRepository.findByUsernameAndActiveTrue(contactUsername);
        Optional<User> userMyInfo = userRepository.findByUsernameAndActiveTrue(myUsername);
        if (!userContacto.isPresent() || !userMyInfo.isPresent()) return false;
        List<Contact> myContacts = contactRepository.findContactsByUsername(userMyInfo.get().getId());
        for (Contact item: myContacts) {
            if(userContacto.get().getId().equals(item.getContact().getId())){
                id = item.getId();
                break;
            }
        }
        contactRepository.deleteById(id);
        return true;
    }
}
