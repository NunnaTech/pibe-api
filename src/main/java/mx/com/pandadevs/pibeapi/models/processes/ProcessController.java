package mx.com.pandadevs.pibeapi.models.processes;

import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/processes")
public class ProcessController implements ControllerInterface<Integer, ProcessDto> {

    @Autowired
    private ProcessService service;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<ProcessDto>> getAll() {
        try {
            return new ResponseEntity(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProcessDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<ProcessDto> save(@RequestBody ProcessDto entity) {
        try {
            return new ResponseEntity(service.save(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    @Override
    public ResponseEntity<ProcessDto> update(@RequestBody ProcessDto entity) {
        try {
            return new ResponseEntity(service.update(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ProcessDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(service.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
