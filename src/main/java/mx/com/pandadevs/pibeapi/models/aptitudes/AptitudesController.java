package mx.com.pandadevs.pibeapi.models.aptitudes;

import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("aptitudes/")
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
