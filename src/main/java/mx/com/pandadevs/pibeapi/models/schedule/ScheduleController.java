package mx.com.pandadevs.pibeapi.models.schedule;

import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleController implements ControllerInterface<Integer, ScheduleDto> {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = "")
    @Override
    public ResponseEntity<List<ScheduleDto>> getAll() {
        try {
            scheduleService.fillInitialData();
            return new ResponseEntity(scheduleService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ScheduleDto> getOne(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(scheduleService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    @Override
    public ResponseEntity<ScheduleDto> save(@RequestBody ScheduleDto entity) {
        try {
            return new ResponseEntity(scheduleService.save(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    @Override
    public ResponseEntity<ScheduleDto> update(@RequestBody ScheduleDto entity) {
        try {
            return new ResponseEntity(scheduleService.update(entity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity(scheduleService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
