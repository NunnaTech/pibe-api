package mx.com.pandadevs.pibeapi.models.vacants.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.benefits.Benefit;
import mx.com.pandadevs.pibeapi.models.benefits.BenefitService;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.mapper.VacantMapper;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import mx.com.pandadevs.pibeapi.utils.enums.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VacantService {

    private Logger logger = LoggerFactory.getLogger(VacantService.class);

    private final VacantMapper mapper;

    @Autowired
    private VacantRepository vacantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BenefitService benefitService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;

    private final String TABLE_NAME = "vacants";

    public VacantService(VacantMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<VacantDto> getAll() {
        return mapper.toVacantsDto(vacantRepository.findAllByActiveIsTrueAndIsPublicIsTrue());
    }

    @Transactional(readOnly = true)
    public Optional<VacantDto> getById(Integer id) {
        Optional<Vacant> vacant = vacantRepository.findByIdAndActiveIsTrue(id);
        return vacant.map(mapper::toVacantDto);
    }

    @Transactional(readOnly = true)
    public List<VacantDto> getByUsername(String username, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER") && auth.get("username").equals(username)) {
            List<Vacant> list = new ArrayList<>();
            User user = userRepository.findByUsername(username);
            if (user != null) list = user.getVacants();
            return mapper.toVacantsDto(list);
        }
        return new ArrayList<>();
    }

    @Transactional
    public Optional<VacantDto> save(VacantDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").equals("ROLE_RECRUITER")) {
            Vacant vacant = mapper.toVacant(entity);
            vacant.setBenefits(fillBenefits(vacant.getBenefits()));
            vacant.setUser(userRepository.findByUsername(entity.getCreator().getUsername()));
            logService.save(new LogDto("{}", logJwtService.parseToJsonObeject(entity), Action.Creacion, userService.getUserByUsername(auth.get("username")), tableService.getById(TABLE_NAME).get()));
            return Optional.of(mapper.toVacantDto(vacantRepository.save(vacant)));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<VacantDto> update(VacantDto entity, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        Vacant vacant = mapper.toVacant(entity);
        Optional<Vacant> updated = vacantRepository.findByIdAndActiveIsTrue(vacant.getId());
        if (auth.get("role").equals("ROLE_RECRUITER") && updated.get().getUser().getUsername().equals(auth.get("username"))) {
            vacant.setBenefits(fillBenefits(vacant.getBenefits()));
            logService.save(new LogDto(logJwtService.parseToJsonObeject(mapper.toVacantDto(updated.get())), logJwtService.parseToJsonObeject(entity), Action.Actualizacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            return Optional.of(mapper.toVacantDto(vacantRepository.save(vacant)));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<VacantDto> partialUpdate(Integer id, Map<Object, Object> fields, String bearerToken) {
        try {
            Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
            Optional<Vacant> updated = vacantRepository.findByIdAndActiveIsTrue(id);
            if (auth.get("role").equals("ROLE_RECRUITER") && updated.get().getUser().getUsername().equals(auth.get("username"))) {
                return updated.map(e -> {
                    fields.forEach((updateField, value) -> {
                        Field field = ReflectionUtils.findField(Vacant.class, (String) updateField);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, e, value);
                    });
                    return Optional.of(mapper.toVacantDto(vacantRepository.saveAndFlush(e)));
                }).orElse(Optional.empty());
            }
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        Optional<Vacant> deleted = vacantRepository.findByIdAndActiveIsTrue(id);
        if (auth.get("role").equals("ROLE_RECRUITER") && deleted.get().getUser().getUsername().equals(auth.get("username"))) {
            logService.save(new LogDto(logJwtService.parseToJsonObeject(mapper.toVacantDto(deleted.get())), "{}", Action.elminacion, userService.getUserByUsername(auth.get("username")), tableService.getByName(TABLE_NAME)));
            deleted.get().setActive(false);
            vacantRepository.save(deleted.get());
            return true;
        }
        return false;
    }

    private List<Benefit> fillBenefits(List<Benefit> list) {
        List<Benefit> benefits = new ArrayList<>();
        for (Benefit b : list) {
            benefits.add(benefitService.getOrSave(b.getName()).get());
        }
        return benefits;
    }
}
