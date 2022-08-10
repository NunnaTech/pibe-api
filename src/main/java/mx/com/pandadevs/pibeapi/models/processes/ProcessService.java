package mx.com.pandadevs.pibeapi.models.processes;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.processes.mapper.ProcessMapper;
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
public class ProcessService {

    private final ProcessMapper mapper;

    @Autowired
    private ProcessRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;

    private final String TABLE_NAME = "processes";

    public ProcessService(ProcessMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ProcessDto> getAll() {
        return mapper.toProcessesDto(repository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    public Optional<ProcessDto> getById(Integer id) {
        Optional<Process> process = repository.findByIdAndActiveIsTrue(id);
        return process.map(mapper::toProcessDto);
    }

    @Transactional
    public Optional<ProcessDto> save(ProcessDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_RECRUITER")) {
            logService.save(new LogDto("{}", logJwtService.parseToJsonObeject(entity), Action.Creacion, userService.getUserByUsername(auth.get("username")), tableService.getById(TABLE_NAME).get()));
            return Optional.of(mapper.toProcessDto(repository.save(mapper.toProcess(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProcessDto> update(ProcessDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_RECRUITER")) {
            Optional<Process> updated = repository.findByIdAndActiveIsTrue(entity.getId());
            logService.save(new LogDto(logJwtService.parseToJsonObeject(updated.get()), logJwtService.parseToJsonObeject(entity), Action.Actualizacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            return Optional.of(mapper.toProcessDto(repository.save(mapper.toProcess(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_RECRUITER")) {
            Optional<Process> deleted = repository.findByIdAndActiveIsTrue(id);
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
        ArrayList<Process> modes = new ArrayList<Process>() {{
            add(new Process("Postulado"));
            add(new Process("CV Visto"));
            add(new Process("Entrevista"));
            add(new Process("Idóneo"));
            add(new Process("Contratado"));
            add(new Process("Finalizado"));
        }};
        repository.saveAll(modes);
    }
}
