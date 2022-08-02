package mx.com.pandadevs.pibeapi.models.benefits;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/benefits")
@Api( tags = "Beneficios")
public class BenefitController {

    @Autowired
    private BenefitService service;

    @GetMapping(value = "")
    public ResponseEntity<List<BenefitDto>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
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
    public ResponseEntity<BenefitDto> save(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody BenefitDto entity) {
        try {
            return service.save(entity, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<BenefitDto> update(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody BenefitDto entity) {
        try {
            return service.update(entity, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String bearerToken, @PathVariable(value = "id") Integer id) {
        try {
            if (service.delete(id, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
