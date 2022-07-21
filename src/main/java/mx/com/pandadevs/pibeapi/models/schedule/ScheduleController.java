package mx.com.pandadevs.pibeapi.models.schedule;

import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleController implements ControllerInterface<ScheduleDto, Integer> {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<ScheduleDto>> getAll() {
        try {
            return new ResponseEntity<>(scheduleService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ScheduleDto> getOne(@PathVariable("id") Integer id) {
        try {
            return scheduleService.getById(id)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<ScheduleDto> save(@Valid @RequestBody ScheduleDto entity) {
        try {
            return new ResponseEntity<>(scheduleService.save(entity), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    @Override
    public ResponseEntity<ScheduleDto> update(@Valid @RequestBody ScheduleDto entity) {
        try {
            return scheduleService.update(entity)
                    .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ScheduleDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        try {
            if (scheduleService.delete(id)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
