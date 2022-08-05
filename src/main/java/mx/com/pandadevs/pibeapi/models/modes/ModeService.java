package mx.com.pandadevs.pibeapi.models.modes;

// Java
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Json
import com.fasterxml.jackson.core.JsonProcessingException;

// Models
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.models.modes.mapper.ModeMapper;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import mx.com.pandadevs.pibeapi.utils.enums.Action;

@Service
public class ModeService {
    
    private Logger logger = LoggerFactory.getLogger(ModeService.class);
    private final ModeMapper mapper;
    private final String TABLE_NAME = "modes";

    @Autowired
    private ModeRepository modeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;


    public ModeService(ModeMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ModeDto> getAll() {
        return mapper.toModesDto(modeRepository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    public Optional<ModeDto> getById(Integer id) {
        Optional<Mode> mode = modeRepository.findByIdAndActiveIsTrue(id);
        return mode.map(mapper::toModeDto);
    }

    @Transactional
    public Optional<ModeDto> save(ModeDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            logService.save(new LogDto("{}", logJwtService.parseToJsonObeject(entity), Action.Creacion, userService.getUserByUsername(auth.get("username")), tableService.getById(TABLE_NAME).get()));
            return Optional.of(mapper.toModeDto(modeRepository.save(mapper.toMode(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ModeDto> update(ModeDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Mode> updated = modeRepository.findByIdAndActiveIsTrue(entity.getId());
            logService.save(new LogDto(logJwtService.parseToJsonObeject(updated.get()), logJwtService.parseToJsonObeject(entity), Action.Actualizacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            return Optional.of(mapper.toModeDto(modeRepository.save(mapper.toMode(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Mode> deletedMode = modeRepository.findByIdAndActiveIsTrue(id);
            if (deletedMode.isPresent()) {
                logService.save(new LogDto(logJwtService.parseToJsonObeject(deletedMode.get()), "{}", Action.Elminacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
                deletedMode.get().setActive(false);
                modeRepository.save(deletedMode.get());
                return true;
            }
        }
        return false;
    }

    public void fillInitialData() {
        if (modeRepository.count() > 0) return;
        ArrayList<Mode> modes = new ArrayList<Mode>() {{
            add(new Mode("Presencial"));
            add(new Mode("Remoto"));
            add(new Mode("Híbrido"));
        }};
        modeRepository.saveAll(modes);
    }
}