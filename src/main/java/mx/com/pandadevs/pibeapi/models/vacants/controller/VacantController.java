package mx.com.pandadevs.pibeapi.models.vacants.controller;

import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.service.VacantService;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("vacants/")
public class VacantController implements ControllerInterface<VacantDto, Integer> {
    @Autowired
    private VacantService vacantService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<VacantDto>> getAll() {
        return new ResponseEntity(vacantService.getAll(), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<VacantDto> getOne(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<VacantDto> save(VacantDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<VacantDto> update(VacantDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<VacantDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Integer id) {
        return null;
    }
}
