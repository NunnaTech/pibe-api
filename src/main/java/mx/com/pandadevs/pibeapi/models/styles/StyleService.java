package mx.com.pandadevs.pibeapi.models.styles;
// Java
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
// Json
import com.fasterxml.jackson.core.JsonProcessingException;

// Models
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.models.styles.mapper.StyleMapper;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import mx.com.pandadevs.pibeapi.utils.enums.Action;
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.services.LogService;
import mx.com.pandadevs.pibeapi.models.logs.services.TableService;

@Service
public class StyleService {
    
    private final StyleMapper mapper;
    private final String TABLE_NAME = "styles";

    @Autowired
    private StyleRepository styleRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private LogJwtService logJwtService;

    @Autowired
    private TableService tableService;

    @Autowired
    private LogService logService;

    public  StyleService(StyleMapper mapper){ this.mapper = mapper;}

    @Transactional(readOnly = true)
    public List<StyleDto> getAll() {
        return mapper.toStylesDto(styleRepository.findAllByActiveTrueOrderByCreatedAtDesc());
    }

    @Transactional(readOnly = true)
    public Optional<StyleDto> getById(Integer id) {
        Optional<Style> aptitude = styleRepository.findById(id);
        return aptitude.map(entity -> {
            return Optional.of(mapper.toStyleDto(entity));
        }).orElse(Optional.empty());
    }
    @Transactional
    public Optional<StyleDto> save(StyleDto entity, String bearerToken) throws JsonProcessingException {
        if (logJwtService.isRecruiter(bearerToken)) {
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
            Style style = mapper.toStyle(entity);
            return Optional.of(mapper.toStyleDto(styleRepository.saveAndFlush(style)));
        }
        return Optional.empty();
        
    }
    @Transactional
    public Optional<StyleDto> update(StyleDto entity, String bearerToken) throws JsonProcessingException {
        Optional<Style> updatedEntity = styleRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            if (logJwtService.isRecruiter(bearerToken)) {
                // Save log
                Map<String, String> auth;
                try {
                    auth = logJwtService.getUsernameAndRole(bearerToken);
                logService.save(
                    new LogDto(
                        logJwtService.parseToJsonObeject(updated),
                        logJwtService.parseToJsonObeject(entity), 
                        Action.Actualizacion, 
                        userService.getUserByUsername(auth.get("username")), 
                        tableService.getByName(TABLE_NAME)));
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                }
                updated = styleRepository.saveAndFlush(mapper.toStyle(entity));
            }
            return Optional.of(mapper.toStyleDto(updated));
        }).orElse(Optional.empty());
    }
    @Transactional
    public Optional<StyleDto> partialUpdate(Integer id, Map<Object, Object> fields, String bearerToken) {
        try {
            Optional<Style> updatedEntity = styleRepository.findById(id);
            return updatedEntity.map(updated -> {
                if (logJwtService.isRecruiter(bearerToken)) {
                    // Check fields
                    fields.forEach((updatedfield, value) -> {
                        // use reflection to get fields updatedfield on manager and set it to value updatedfield
                        Field field = ReflectionUtils.findField(Style.class, (String) updatedfield);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, updated, value);
                    });
                    // Save log
                    Map<String, String> auth;
                    try {
                        auth = logJwtService.getUsernameAndRole(bearerToken);
                    logService.save(
                        new LogDto(
                            logJwtService.parseToJsonObeject(updatedEntity),
                            logJwtService.parseToJsonObeject(updated), 
                            Action.Actualizacion, 
                            userService.getUserByUsername(auth.get("username")), 
                            tableService.getByName(TABLE_NAME)));
                    } catch (JsonProcessingException e) {
                            e.printStackTrace();
                    }
                    return Optional.of(mapper.toStyleDto(styleRepository.saveAndFlush(updated)));
                }
                return Optional.of(mapper.toStyleDto(updated));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }
    @Transactional
    public Boolean delete(Integer id, String bearerToken) {
        return styleRepository.findStyleByIdAndActiveTrue(id).map(entity -> {
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
                styleRepository.save(entity);
            }
            return !entity.getActive();
        }).orElse(false);
    }
}
