package mx.com.pandadevs.pibeapi.models.benefits;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import mx.com.pandadevs.pibeapi.utils.enums.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.models.benefits.mapper.BenefitMapper;

@Service
public class BenefitService {

    private final BenefitMapper mapper;
    @Autowired
    private BenefitRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;

    private final String TABLE_NAME = "benefits";

    public BenefitService(BenefitMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<BenefitDto> getAll() {
        return mapper.toBenefitsDto(repository.findAllByActiveIsTrue());
    }

    @Transactional(readOnly = true)
    public Optional<BenefitDto> getById(Integer id) {
        Optional<Benefit> benefit = repository.findByIdAndActiveIsTrue(id);
        return benefit.map(mapper::toBenefitDto);
    }

    @Transactional
    public Optional<BenefitDto> save(BenefitDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            logService.save(new LogDto("{}", logJwtService.parseToJsonObeject(entity), Action.Creacion, userService.getUserByUsername(auth.get("username")), tableService.getById(TABLE_NAME).get()));
            Benefit benefit = mapper.toBenefit(entity);
            return Optional.of(mapper.toBenefitDto(repository.saveAndFlush(benefit)));
        }
        return Optional.empty();

    }

    @Transactional
    public Optional<BenefitDto> update(BenefitDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Benefit> updated = repository.findByIdAndActiveIsTrue(entity.getId());
            logService.save(new LogDto(logJwtService.parseToJsonObeject(updated.get()), logJwtService.parseToJsonObeject(entity), Action.Actualizacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            return Optional.of(mapper.toBenefitDto(repository.save(mapper.toBenefit(entity))));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Benefit> getOrSave(String name) {
        String _name = name.substring(0, 1).toUpperCase() + name.substring(1);
        Optional<Benefit> finded = repository.findByNameLikeIgnoreCase(_name);
        if (finded.isPresent()) return finded;
        return Optional.of(repository.save(new Benefit(_name)));
    }

    @Transactional
    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Optional<Benefit> deletedMode = repository.findByIdAndActiveIsTrue(id);
            if (deletedMode.isPresent()) {
                logService.save(new LogDto(logJwtService.parseToJsonObeject(deletedMode.get()), "{}", Action.Elminacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
                deletedMode.get().setActive(false);
                repository.save(deletedMode.get());
                return true;
            }
        }
        return false;
    }

    public void fillInitialData() {
        if (repository.count() > 0) return;
        ArrayList<Benefit> list = new ArrayList<Benefit>() {{
            add(new Benefit("Certificaciones"));
            add(new Benefit("Seguro médico"));
            add(new Benefit("Vacaciones"));
            add(new Benefit("Préstamos"));
            add(new Benefit("Cursos"));
            add(new Benefit("Vales de despensa"));
        }};
        repository.saveAll(list);
    }
}
