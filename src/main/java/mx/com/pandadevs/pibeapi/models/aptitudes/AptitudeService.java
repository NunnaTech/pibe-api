package mx.com.pandadevs.pibeapi.models.aptitudes;
// Java
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

// Models
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.aptitudes.mapper.AptitudeMapper;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import mx.com.pandadevs.pibeapi.utils.enums.Action;

@Service
public class AptitudeService {
    private final AptitudeMapper mapper;
    private final String TABLE_NAME = "aptitudes";

    @Autowired
    private AptitudeRepository aptitudeRepository;
    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;
    
    @Autowired
    private UserService userService;

    public AptitudeService(AptitudeMapper mapper){
        this.mapper = mapper;
    }

    public List<AptitudeDto> getAll() {
        return mapper.toAptitudesDto(aptitudeRepository.findAllByActiveTrueOrderByCreatedAtAsc());
    }

    public Optional<AptitudeDto> getById(Integer id) {
        Optional<Aptitude> aptitude = aptitudeRepository.findStyleByIdAndActiveTrue(id);
        return aptitude.map(entity -> {
            return Optional.of(mapper.toAptitudeDto(entity));
        }).orElse(Optional.empty());
    }

    public AptitudeDto save(AptitudeDto entity, String bearerToken) throws JsonProcessingException {
        if (logJwtService.isCandidate(bearerToken)) {
            // Save log
            Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
            logService.save(
                new LogDto(
                    "{}",
                    logJwtService.parseToJsonObeject(entity),
                    Action.Creacion,
                    userService.getUserByUsername(auth.get("username")),
                    tableService.getById(TABLE_NAME).get()
                )
            );
            Aptitude aptitude = mapper.toAptitude(entity);
            return mapper.toAptitudeDto(aptitudeRepository.saveAndFlush(aptitude));
        }
        return null;
    }

    public List<AptitudeDto> save(List<AptitudeDto> entities) {
        return mapper.toAptitudesDto(aptitudeRepository.saveAll(mapper.toAptitudes(entities)));
    }

    public Optional<AptitudeDto> update(AptitudeDto entity, String bearerToken) throws JsonProcessingException {
        Optional<Aptitude> updatedEntity = aptitudeRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            if (logJwtService.isCandidate(bearerToken)) {
                // Save log
                Map<String, String> auth;
                try {
                    auth = logJwtService.getUsernameAndRole(bearerToken);
                    updated = aptitudeRepository.saveAndFlush(mapper.toAptitude(entity));
                    logService.save(
                        new LogDto(
                            logJwtService.parseToJsonObeject(updated),
                            logJwtService.parseToJsonObeject(entity),
                            Action.Actualizacion,
                            userService.getUserByUsername(auth.get("username")),
                            tableService.getById(TABLE_NAME).get()
                        )
                    );
                } catch (JsonProcessingException e) {
                  
                    }
                }
                return Optional.of(mapper.toAptitudeDto(updated));
        }).orElse(Optional.empty());
    }

    public Optional<AptitudeDto> partialUpdate(Integer id, Map<Object, Object> fields, String bearerToken) throws JsonProcessingException {
        try {
            Optional<Aptitude> updatedEntity = aptitudeRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Aptitude.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toAptitudeDto(aptitudeRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    public Boolean delete(Integer id, String bearerToken) throws JsonProcessingException {
        return aptitudeRepository.findStyleByIdAndActiveTrue(id).map(entity -> {
            if (logJwtService.isRecruiter(bearerToken)) {
                // Save log
                Map<String, String> auth;
                try {
                    auth = logJwtService.getUsernameAndRole(bearerToken);
                logService.save(
                    new LogDto(
                        logJwtService.parseToJsonObeject(entity),
                        logJwtService.parseToJsonObeject("{}"), 
                        Action.Elminacion, 
                        userService.getUserByUsername(auth.get("username")), 
                        tableService.getByName(TABLE_NAME)));
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                }
                entity.setActive(false);
                aptitudeRepository.save(entity);
            }
            return !entity.getActive();
        }).orElse(false);
    }
}
