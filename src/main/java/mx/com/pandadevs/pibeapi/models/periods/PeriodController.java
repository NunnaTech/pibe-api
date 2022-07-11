package mx.com.pandadevs.pibeapi.models.periods;

import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/periods")
public class PeriodController implements ControllerInterface<Integer, PeriodDto> {

    @Autowired
    private PeriodService periodService;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<PeriodDto>> getAll() {
        try {
            return new ResponseEntity(periodService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<PeriodDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(periodService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<PeriodDto> save(@RequestBody PeriodDto entity) {
        try {
            return new ResponseEntity(periodService.save(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    @Override
    public ResponseEntity<PeriodDto> update(@RequestBody PeriodDto entity) {
        try {
            return new ResponseEntity(periodService.update(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PeriodDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(periodService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}