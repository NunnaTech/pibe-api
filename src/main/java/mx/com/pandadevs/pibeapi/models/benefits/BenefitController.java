package mx.com.pandadevs.pibeapi.models.benefits;

import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/benefits")
public class BenefitController implements ControllerInterface<Integer, BenefitDto> {

    @Autowired private BenefitService service;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<BenefitDto>> getAll() {
        try {
            return new ResponseEntity(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<BenefitDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<BenefitDto> save(@RequestBody BenefitDto entity) {
        try {
            return new ResponseEntity(service.save(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "")
    @Override
    public ResponseEntity<BenefitDto> update(@RequestBody BenefitDto entity) {
        try {
            return new ResponseEntity(service.update(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<BenefitDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(service.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
