package mx.com.pandadevs.pibeapi.models.benefits;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/benefits")
@Api( tags = "Benefits")
public class BenefitController implements ControllerInterface<BenefitDto,Integer> {

    @Autowired
    private BenefitService service;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<BenefitDto>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<BenefitDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return service.getById(id)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<BenefitDto> save(@Valid @RequestBody BenefitDto entity) {
        try {
            return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "")
    @Override
    public ResponseEntity<BenefitDto> update(@Valid @RequestBody BenefitDto entity) {
        try {
            return service.update(entity)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
            if (service.delete(id)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
