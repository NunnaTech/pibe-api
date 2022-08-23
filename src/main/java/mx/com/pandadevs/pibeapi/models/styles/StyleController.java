package mx.com.pandadevs.pibeapi.models.styles;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
@RestController
@RequestMapping("styles/")
@Api( tags = "Styles")
public class StyleController {
    @Autowired
    private StyleService styleService;
    
    @GetMapping("")
    public ResponseEntity<List<StyleDto>> getAll() {
        return new ResponseEntity(styleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StyleDto> getOne(@PathVariable("id") Integer id) {
        return styleService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<StyleDto> save(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody StyleDto entity) {
        try {
            return styleService.save(entity, bearerToken)
                .map(style -> new ResponseEntity<>(style, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<StyleDto> update(@RequestHeader("Authorization") String bearerToken,@Valid@RequestBody StyleDto entity) {
        try {
            return styleService.update(entity,bearerToken)
                    .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StyleDto> partialUpdate(@RequestHeader("Authorization") String bearerToken,@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        return styleService.partialUpdate(id, fields, bearerToken)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@RequestHeader("Authorization") String bearerToken,@PathVariable("id") Integer id) {
        boolean deleted = styleService.delete(id, bearerToken);
        if (deleted) return new ResponseEntity(deleted, HttpStatus.OK);
        else return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
    }
}
