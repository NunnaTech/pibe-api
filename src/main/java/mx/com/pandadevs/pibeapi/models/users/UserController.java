package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.List;
import java.util.Map;

// Spring
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
import mx.com.pandadevs.pibeapi.utils.ControllerInterface;

@RestController
@RequestMapping("user/")
public class UserController implements ControllerInterface<User> {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") Long id) {
        return userService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User entity) {
        return new ResponseEntity<>(userService.save(entity), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<User> update(@RequestBody User entity) {
        return userService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<User> partialUpdate(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields) {
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