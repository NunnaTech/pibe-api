package mx.com.pandadevs.pibeapi.models.styles;

import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("style/")
public class StyleController implements ControllerInterface<StyleDto, Integer> {
    @Autowired
    private StyleService styleService;
    
    @Override
    @GetMapping("")
    public ResponseEntity<List<StyleDto>> getAll() {
        return new ResponseEntity(styleService.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<StyleDto> getOne(@PathVariable("id") Integer id) {
        return styleService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<StyleDto> save(@RequestBody StyleDto entity) {
        return new ResponseEntity<>(styleService.save(entity), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<StyleDto> update(@RequestBody StyleDto entity) {
        return styleService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<StyleDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        return styleService.partialUpdate(id, fields)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean deleted = styleService.delete(id);
        if (deleted) return new ResponseEntity(deleted, HttpStatus.OK);
        else return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
    }
}
