package mx.com.pandadevs.pibeapi.models.periods;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.models.periods.mapper.PeriodMapper;
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
public class PeriodService {

    private final PeriodMapper mapper;

    @Autowired
    private PeriodRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;

    private final String TABLE_NAME = "periods";

    public PeriodService(PeriodMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<PeriodDto> getAll() {
        return mapper.toPeriodsDto(repository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    public Optional<PeriodDto> getById(Integer id) {
        Optional<Period> period = repository.findByIdAndActiveIsTrue(id);
        return period.map(mapper::toPeriodDto);
    }

    @Transactional
    public Optional<PeriodDto> save(PeriodDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            logService.save(new LogDto("{}", logJwtService.parseToJsonObeject(entity), Action.Creacion, userService.getUserByUsername(auth.get("username")), tableService.getById(TABLE_NAME).get()));
            return Optional.of(mapper.toPeriodDto(repository.save(mapper.toPeriod(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<PeriodDto> update(PeriodDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Period> updated = repository.findByIdAndActiveIsTrue(entity.getId());
            logService.save(new LogDto(logJwtService.parseToJsonObeject(updated.get()), logJwtService.parseToJsonObeject(entity), Action.Actualizacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            return Optional.of(mapper.toPeriodDto(repository.save(mapper.toPeriod(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Period> deleted = repository.findByIdAndActiveIsTrue(id);
            if (deleted.isPresent()) {
                logService.save(new LogDto(logJwtService.parseToJsonObeject(deleted.get()), "{}", Action.elminacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
                deleted.get().setActive(false);
                repository.save(deleted.get());
                return true;
            }
        }
        return false;
    }

    public void fillInitialData() {
        if (repository.count() > 0) return;
        ArrayList<Period> modes = new ArrayList<Period>() {{
            add(new Period("Hora"));
            add(new Period("Día"));
            add(new Period("Semanal"));
            add(new Period("Quincenal"));
            add(new Period("Mensual"));
            add(new Period("Anual"));
        }};
        repository.saveAll(modes);
    }
}
