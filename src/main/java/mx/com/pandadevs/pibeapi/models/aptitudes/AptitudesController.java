package mx.com.pandadevs.pibeapi.models.aptitudes;

// Java
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

// Spring
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

// Models
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;

@RestController
@RequestMapping("aptitudes/")
@Api( tags = "Aptitudes")
public class AptitudesController {
    @Autowired
    private AptitudeService aptitudeService;

    @GetMapping("")
    public ResponseEntity<List<AptitudeDto>> getAll() {
            return new ResponseEntity(aptitudeService.getAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<AptitudeDto> getOne(@PathVariable("id") Integer id) {
        return aptitudeService.getById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<AptitudeDto> save(@RequestHeader("Authorization") String bearerToken,@Valid@RequestBody AptitudeDto entity) {
        try {
            return new ResponseEntity<>(aptitudeService.save(entity, bearerToken), HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("")
    public ResponseEntity<AptitudeDto> update(@RequestHeader("Authorization") String bearerToken,@Valid@RequestBody AptitudeDto entity) {
        try {
            return aptitudeService.update(entity, bearerToken)
                    .map(updatedEntity -> new ResponseEntity<>(updatedEntity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AptitudeDto> partialUpdate(@RequestHeader("Authorization") String bearerToken,@Valid@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            return aptitudeService.partialUpdate(id, fields, bearerToken)
                    .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@RequestHeader("Authorization") String bearerToken,@PathVariable("id") Integer id) {
        boolean deleted = false;
        try {
            deleted = aptitudeService.delete(id, bearerToken);
            if (deleted) return new ResponseEntity(deleted, HttpStatus.OK);
            else return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            return new ResponseEntity(deleted, HttpStatus.NOT_FOUND);
        }
       
    }
}
