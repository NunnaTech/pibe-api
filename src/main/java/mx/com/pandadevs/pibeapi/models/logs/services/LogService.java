package mx.com.pandadevs.pibeapi.models.logs.services;
// Java
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
// Models
import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.entities.Log;
import mx.com.pandadevs.pibeapi.models.logs.mapper.LogMapper;
import mx.com.pandadevs.pibeapi.models.logs.repository.LogRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class LogService implements ServiceInterface<Long, LogDto> {
    private final LogMapper mapper;

    @Autowired
    private LogRepository logRepository;

    public LogService(LogMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<LogDto> getAll() {
        return mapper.toLogsDto(logRepository.findAll());
    }

    @Override
    public Optional<LogDto> getById(Long id) {
        Optional<Log> log = logRepository.findById(id);
        return log.map(entity -> {
            return Optional.of(mapper.toLogDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public LogDto save(LogDto entity) {
        Log log = mapper.toLog(entity);
        return mapper.toLogDto(logRepository.saveAndFlush(log));
    }

    @Override
    public Optional<LogDto> update(LogDto entity) {
        Optional<Log> updatedEntity = logRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            logRepository.saveAndFlush(updated);
            return Optional.of(mapper.toLogDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<LogDto> partialUpdate(Long id, Map<Object, Object> fields) {
        try {
            Optional<Log> updatedEntity = logRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Log.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toLogDto(logRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return logRepository.findById(id).map(entity -> {
            logRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
