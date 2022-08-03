package mx.com.pandadevs.pibeapi.models.vacants.controller;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.UserVacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.service.UserVacantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user-vacants")
@Api(tags = "User Vacants")
public class UserVacantContoller {

    private Logger logger = LoggerFactory.getLogger(UserVacantContoller.class);

    @Autowired
    private UserVacantService service;

    @GetMapping(value = "/{username}/vacants")
    public ResponseEntity<List<UserVacantDto>> getVacantsByUser(@RequestHeader("Authorization") String bearerToken,
                                                                @PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getVacantsByUser(username, bearerToken), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}/users")
    public ResponseEntity<List<VacantProcessDto>> getUsersByVacant(@RequestHeader("Authorization") String bearerToken,
                                                                   @PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(service.getUsersByVacant(id, bearerToken), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}/{username}")
    public ResponseEntity<Boolean> applyToVacant(@RequestHeader("Authorization") String bearerToken,
                                                 @PathVariable("id") Integer id,
                                                 @PathVariable("username") String username) {
        try {
            if (service.applyToVacant(id, username, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}/{username}")
    public ResponseEntity<Boolean> processToVacant(@RequestHeader("Authorization") String bearerToken,
                                                   @PathVariable("id") Integer id,
                                                   @RequestBody ProcessDto processDto) {
        try {
            if (service.processToVacant(id, processDto, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUserToVacant(@RequestHeader("Authorization") String bearerToken,
                                                      @PathVariable("id") Integer id) {
        try {
            if (service.deleteUserToVacant(id, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
