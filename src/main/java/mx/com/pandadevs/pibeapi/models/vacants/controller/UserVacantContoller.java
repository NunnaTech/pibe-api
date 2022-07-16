package mx.com.pandadevs.pibeapi.models.vacants.controller;

import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.UserVacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.service.UserVacantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user-vacants")
public class UserVacantContoller {

    @Autowired
    private UserVacantService service;

    @GetMapping(value = "/{username}/vacants")
    public ResponseEntity<List<UserVacantDto>> getVacantsByUser(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getVacantsByUser(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}/users")
    public ResponseEntity<List<VacantProcessDto>> getUsersByVacant(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(service.getUsersByVacant(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}/{username}")
    public ResponseEntity<Boolean> applyToVacant(@PathVariable("id") Integer id,
                                                 @PathVariable("username") String username) {
        try {
            if (service.applyToVacant(id, username)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}/{username}")
    public ResponseEntity<Boolean> processToVacant(@PathVariable("id") Integer id,
                                                   @RequestBody ProcessDto processDto) {
        try {
            if (service.processToVacant(id, processDto)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUserToVacant(@PathVariable("id") Integer id) {
        try {
            if (service.deleteUserToVacant(id)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
