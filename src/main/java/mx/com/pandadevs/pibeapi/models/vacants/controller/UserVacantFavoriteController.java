package mx.com.pandadevs.pibeapi.models.vacants.controller;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.service.UserVacantFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/favorite/vacants")
@Api(tags = "User Vacants Favorite")
public class UserVacantFavoriteController {
    @Autowired
    private UserVacantFavoriteService service;

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<VacantDto>> getAll(@RequestHeader("Authorization") String bearerToken, @PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getAll(username, bearerToken), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{username}/{id}")
    public ResponseEntity<VacantDto> getOne(@RequestHeader("Authorization") String bearerToken, @PathVariable("username") String username, @PathVariable("id") Integer id) {
        try {
            return service.getById(username, id, bearerToken)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{username}/{id}")
    public ResponseEntity<Boolean> save(@RequestHeader("Authorization") String bearerToken, @PathVariable("username") String username, @PathVariable("id") Integer id) {
        try {
            if (service.save(username, id, bearerToken)) return new ResponseEntity<>(true, HttpStatus.CREATED);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{username}/{id}")
    public ResponseEntity<Boolean> delete(@RequestHeader("Authorization") String bearerToken, @PathVariable("username") String username, @PathVariable("id") Integer id) {
        try {
            if (service.delete(username, id, bearerToken)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
