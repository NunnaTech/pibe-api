package mx.com.pandadevs.pibeapi.models.modes;

import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/modes")
public class ModeController {

    private Logger logger = LoggerFactory.getLogger(ModeController.class);

    @Autowired
    private LogJwtService logJwtService;
    @Autowired
    private ModeService modeService;

    @GetMapping(value = "")
    public ResponseEntity<List<ModeDto>> getAll(@RequestHeader("Authorization") String bearerToken) {
        try {
            logger.error(logJwtService.getOnlyUsername(bearerToken));
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
            String username = logJwtService.getOnlyUsername(bearerToken);
            return new ResponseEntity<>(modeService.save(entity, username), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<ModeDto> update(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody ModeDto entity) {
        try {
            String username = logJwtService.getOnlyUsername(bearerToken);
            return modeService.update(entity, username)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ModeDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Integer id) {
        try {
            String username = logJwtService.getOnlyUsername(bearerToken);
            if (modeService.delete(id, username)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
