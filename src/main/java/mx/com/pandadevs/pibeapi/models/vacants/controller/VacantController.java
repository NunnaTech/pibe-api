package mx.com.pandadevs.pibeapi.models.vacants.controller;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.service.VacantService;
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
@RequestMapping(value = "/vacants")
@Api(tags = "Vacantes")
public class VacantController {
    private Logger logger = LoggerFactory.getLogger(VacantController.class);

    @Autowired
    private VacantService service;

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<List<VacantDto>> getByUsername(@RequestHeader("Authorization") String bearerToken, @PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getByUsername(username, bearerToken), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<VacantDto>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VacantDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return service.getById(id)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<VacantDto> save(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody VacantDto entity) {
        try {
            return service.save(entity, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.toString());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<VacantDto> update(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody VacantDto entity) {
        try {
            return service.update(entity, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.toString());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VacantDto> partialUpdate(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            return service.partialUpdate(id, fields, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Integer id) {
        try {
            if (service.delete(id, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.toString());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
