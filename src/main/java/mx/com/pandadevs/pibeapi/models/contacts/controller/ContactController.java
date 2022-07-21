package mx.com.pandadevs.pibeapi.models.contacts.controller;

import mx.com.pandadevs.pibeapi.models.contacts.dto.ContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.dto.SingleContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import mx.com.pandadevs.pibeapi.models.contacts.entity.ContactFk;
import mx.com.pandadevs.pibeapi.models.contacts.mapper.ContactMapper;
import mx.com.pandadevs.pibeapi.models.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactMapper mapper;
    @Autowired
    private ContactService contactService;

    @GetMapping("/{username}")
    public ResponseEntity<List<SingleContactDto>>getContactsByUsername(@PathVariable("username") String username){
        return  contactService.findContactsByUsername(username)
                .map( entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{myusername}/{contactusername}")
    public  ResponseEntity<SingleContactDto> saveContactByUsername(@PathVariable("myusername") String myUsername, @PathVariable("contactusername") String contactUsername){
        return contactService.save(myUsername, contactUsername)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{myusername}/{contactusername}")
    public ResponseEntity<Boolean> deleteContactByUsername(@PathVariable("myusername") String myUsername, @PathVariable("contactusername") String contactUsername){
        return new ResponseEntity<>(contactService.delete(myUsername, contactUsername), HttpStatus.OK);
    }
}
