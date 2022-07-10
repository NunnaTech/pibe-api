package mx.com.pandadevs.pibeapi.models.contacts.service;

import mx.com.pandadevs.pibeapi.models.contacts.dto.ContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.entity.ContactFk;
import mx.com.pandadevs.pibeapi.models.contacts.mapper.ContactMapper;
import mx.com.pandadevs.pibeapi.models.contacts.repository.ContactRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ContactService implements ServiceInterface<ContactFk,ContactDto> {
    private final ContactMapper mapper;
    @Autowired
    private ContactRepository contactRepository;

    public ContactService(ContactMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<ContactDto> getAll() {
        return null;
    }
    public List<ContactDto> getAllByUserid(String username) {
        return mapper.toContactsDto(contactRepository.findContactByContactUsername(username));
    }

    @Override
    public Optional<ContactDto> getById(ContactFk id) {
        return Optional.empty();
    }

    @Override
    public ContactDto save(ContactDto entity) {
        return null;
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
}
