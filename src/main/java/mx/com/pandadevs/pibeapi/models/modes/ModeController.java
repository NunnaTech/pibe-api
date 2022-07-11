package mx.com.pandadevs.pibeapi.models.modes;

import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/modes")
public class ModeController implements ControllerInterface<Integer, ModeDto> {

    @Autowired
    private ModeService modeService;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<ModeDto>> getAll() {
        try {
            return new ResponseEntity(modeService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ModeDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(modeService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<ModeDto> save(@RequestBody ModeDto entity) {
        try {
            return new ResponseEntity(modeService.save(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("")
    @Override
    public ResponseEntity<ModeDto> update(@RequestBody ModeDto entity) {
        try {
            return new ResponseEntity(modeService.update(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ModeDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(modeService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
