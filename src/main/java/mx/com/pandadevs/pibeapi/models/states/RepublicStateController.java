package mx.com.pandadevs.pibeapi.models.states;

import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/states")
public class RepublicStateController implements ControllerInterface<Integer, RepublicStateDto> {

    @Autowired
    private RepublicStateService stateService;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<RepublicStateDto>> getAll() {
        try {
            return new ResponseEntity(stateService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<RepublicStateDto> getOne(@PathVariable(value = "id") Integer id) {
        try {
            return new ResponseEntity(stateService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<RepublicStateDto> save(RepublicStateDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<RepublicStateDto> update(RepublicStateDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<RepublicStateDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Integer id) {
        return null;
    }
}
