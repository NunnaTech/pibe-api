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

// Models
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.aptitudes.mapper.AptitudeMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class AptitudeService implements ServiceInterface<Integer,AptitudeDto> {
    private final AptitudeMapper mapper;

    @Autowired
    private AptitudeRepository aptitudeRepository;

    public AptitudeService(AptitudeMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<AptitudeDto> getAll() {
        return mapper.toAptitudesDto(aptitudeRepository.findAllByActiveTrueOrderByCreatedAtAsc());
    }

    @Override
    public Optional<AptitudeDto> getById(Integer id) {
        Optional<Aptitude> aptitude = aptitudeRepository.findStyleByIdAndActiveTrue(id);
        return aptitude.map(entity -> {
            return Optional.of(mapper.toAptitudeDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public AptitudeDto save(AptitudeDto entity) {
        Aptitude aptitude = mapper.toAptitude(entity);
        return mapper.toAptitudeDto(aptitudeRepository.saveAndFlush(aptitude));
    }

    @Override
    public Optional<AptitudeDto> update(AptitudeDto entity) {
        Optional<Aptitude> updatedEntity = aptitudeRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            return Optional.of(mapper.toAptitudeDto(
                    aptitudeRepository.saveAndFlush(
                            mapper.toAptitude(entity))));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<AptitudeDto> partialUpdate(Integer id, Map<Object, Object> fields) {
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

    @Override
    public Boolean delete(Integer id) {
        return aptitudeRepository.findStyleByIdAndActiveTrue(id).map(entity -> {
            entity.setActive(false);
            aptitudeRepository.save(entity);
            return true;
        }).orElse(false);
    }
}
