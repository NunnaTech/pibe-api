package mx.com.pandadevs.pibeapi.models.vacants.controller;

import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.service.UserVacantFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users/favorite/vacants")
public class UserVacantFavoriteController {
    @Autowired
    private UserVacantFavoriteService service;

    @GetMapping(value = "/{username}")
    public ResponseEntity<List<VacantDto>> getAll(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(service.getAll(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{username}/{id}")
    public ResponseEntity<VacantDto> getOne(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        try {
            return service.getById(username, id).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{username}/{id}")
    public ResponseEntity<Boolean> save(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        try {
            if (service.save(username, id)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{username}/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        try {
            if (service.delete(username, id)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
