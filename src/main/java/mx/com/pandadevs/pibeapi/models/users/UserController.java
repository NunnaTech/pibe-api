package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.List;
import java.util.Map;

// Spring
import mx.com.pandadevs.pibeapi.models.users.controller.ResumeUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Models
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import mx.com.pandadevs.pibeapi.models.contacts.dto.ContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.service.ContactService;
import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;
@RestController
@RequestMapping("user/")
public class UserController extends ResumeUserController implements ControllerInterface<UserDto, Long> {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<UserDto> getOne(@PathVariable("id") Long id) {
        return userService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserProfileDto> getOneById(@PathVariable("id") Long id) {
        return userService.getByProfileById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{username}/contacts")
    public ResponseEntity<ContactDto> getContactsById(@PathVariable("username") String username) {
        return new ResponseEntity(contactService.getAllByUserid(username), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileDto> getOneByUsername(@PathVariable("username") String username) {
        return userService.getByUsername(username)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{username}/notifications")
    public ResponseEntity<List<UserNotificationDto>> getNotificationsByUsername(@PathVariable("username") String username) {
        return new ResponseEntity(userService.getNotificationsByUsername(username), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto entity) {
        return new ResponseEntity<>(userService.save(entity), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<UserDto> update(@RequestBody UserDto entity) {
        return userService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partialUpdate(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields) {
        return userService.partialUpdate(id, fields)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boolean deleted = userService.delete(id);
        if (deleted) return new ResponseEntity(deleted, HttpStatus.OK);
        else return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
    }
}
