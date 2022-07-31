package mx.com.pandadevs.pibeapi.models.modes;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/modes")
@Api( tags = "Modos")
public class ModeController {

    @Autowired
    private ModeService modeService;

    @GetMapping(value = "")
    public ResponseEntity<List<ModeDto>> getAll() {
        try {
            return new ResponseEntity<>(modeService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModeDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return modeService.getById(id)
                    .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<ModeDto> save(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody ModeDto entity) {
        try {
            return new ResponseEntity<>(modeService.save(entity, bearerToken), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<ModeDto> update(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody ModeDto entity) {
        try {
            return modeService.update(entity, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Integer id) {
        try {
            if (modeService.delete(id, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
