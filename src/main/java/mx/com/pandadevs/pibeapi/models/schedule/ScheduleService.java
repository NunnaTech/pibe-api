package mx.com.pandadevs.pibeapi.models.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import mx.com.pandadevs.pibeapi.models.schedule.mapper.ScheduleMapper;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import mx.com.pandadevs.pibeapi.utils.enums.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleMapper mapper;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;

    private final String TABLE_NAME = "schedule";

    public ScheduleService(ScheduleMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ScheduleDto> getAll() {
        return mapper.toSchedulesDto(scheduleRepository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    public Optional<ScheduleDto> getById(Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findByIdAndActiveIsTrue(id);
        return schedule.map(mapper::toScheduleDto);
    }

    public Optional<ScheduleDto> save(ScheduleDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            logService.save(new LogDto("{}", logJwtService.parseToJsonObeject(entity), Action.Creacion, userService.getUserByUsername(auth.get("username")), tableService.getById(TABLE_NAME).get()));
            return Optional.of(mapper.toScheduleDto(scheduleRepository.save(mapper.toSchedule(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ScheduleDto> update(ScheduleDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Schedule> updatedSchedule = scheduleRepository.findByIdAndActiveIsTrue(entity.getId());
            logService.save(new LogDto(logJwtService.parseToJsonObeject(updatedSchedule.get()), logJwtService.parseToJsonObeject(entity), Action.Actualizacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            return Optional.of(mapper.toScheduleDto(scheduleRepository.save(mapper.toSchedule(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Schedule> deletedSchedule = scheduleRepository.findByIdAndActiveIsTrue(id);
            if (deletedSchedule.isPresent()) {
                logService.save(new LogDto(logJwtService.parseToJsonObeject(deletedSchedule.get()), "{}", Action.Elminacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
                deletedSchedule.get().setActive(false);
                scheduleRepository.save(deletedSchedule.get());
                return true;
            }
        }
        return false;
    }

    public void fillInitialData() {
        if (scheduleRepository.count() > 0) return;
        ArrayList<Schedule> modes = new ArrayList<Schedule>() {{
            add(new Schedule("Tiempo completo"));
            add(new Schedule("Medio tiempo"));
            add(new Schedule("Fines de semana"));
            add(new Schedule("Horario flexible"));
        }};
        scheduleRepository.saveAll(modes);
    }

}
