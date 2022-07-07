package mx.com.pandadevs.pibeapi.models.styles;
// Java
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Spring
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Models
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.models.styles.mapper.StyleMapper;
@Service
public class StyleService implements ServiceInterface<Integer, StyleDto> {
    private final StyleMapper mapper;
    @Autowired
    private StyleRepository styleRepository;
    public  StyleService(StyleMapper mapper){ this.mapper = mapper;}

    @Override
    public List<StyleDto> getAll() {
        return mapper.toStylesDto(styleRepository.findAll());
    }

    @Override
    public Optional<StyleDto> getById(Integer id) {
        Optional<Style> aptitude = styleRepository.findById(id);
        return aptitude.map(entity -> {
            return Optional.of(mapper.toStyleDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public StyleDto save(StyleDto entity) {
        Style style = mapper.toStyle(entity);
        return mapper.toStyleDto(styleRepository.saveAndFlush(style));
    }

    @Override
    public Optional<StyleDto> update(StyleDto entity) {
        Optional<Style> updatedEntity = styleRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            styleRepository.saveAndFlush(updated);
            return Optional.of(mapper.toStyleDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<StyleDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Style> updatedEntity = styleRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Style.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toStyleDto(styleRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return styleRepository.findById(id).map(entity -> {
            styleRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
