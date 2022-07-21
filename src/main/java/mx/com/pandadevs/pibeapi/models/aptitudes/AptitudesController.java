package mx.com.pandadevs.pibeapi.models.aptitudes;

// Java
import java.util.List;
import java.util.Map;
// Spring
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

// Models
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;

@RestController
@RequestMapping("aptitudes/")
@Api( tags = "Aptitudes")
public class AptitudesController implements ControllerInterface<AptitudeDto, Integer> {
    @Autowired
    private AptitudeService aptitudeService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<AptitudeDto>> getAll() {
            return new ResponseEntity(aptitudeService.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<AptitudeDto> getOne(@PathVariable("id") Integer id) {
        return aptitudeService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<AptitudeDto> save(@RequestBody AptitudeDto entity) {
        return new ResponseEntity<>(aptitudeService.save(entity), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("")
    public ResponseEntity<AptitudeDto> update(@RequestBody AptitudeDto entity) {
        return aptitudeService.update(entity)
                .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PatchMapping("{id}")
    public ResponseEntity<AptitudeDto> partialUpdate(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        return aptitudeService.partialUpdate(id, fields)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        boolean deleted = aptitudeService.delete(id);
        if (deleted) return new ResponseEntity(deleted, HttpStatus.OK);
        else return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
    }
}
