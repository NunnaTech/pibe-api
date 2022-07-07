package mx.com.pandadevs.pibeapi.models.languages.services;
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
import mx.com.pandadevs.pibeapi.models.languages.dto.LanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.Language;
import mx.com.pandadevs.pibeapi.models.languages.mapper.LanguageMapper;
import mx.com.pandadevs.pibeapi.models.languages.repository.LanguageRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class LanguageService implements ServiceInterface<Integer, LanguageDto> {
    private final LanguageMapper mapper;
    @Autowired
    private LanguageRepository languageRepository;
    public LanguageService(LanguageMapper mapper){ this.mapper = mapper;}
    @Override
    public List<LanguageDto> getAll() {
        return mapper.toLanguagesDto(languageRepository.findAll());
    }

    @Override
    public Optional<LanguageDto> getById(Integer id) {
        Optional<Language> language = languageRepository.findById(id);
        return language.map(entity -> {
            return Optional.of(mapper.toLanguageDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public LanguageDto save(LanguageDto entity) {
        Language language = mapper.toLanguage(entity);
        return mapper.toLanguageDto(languageRepository.saveAndFlush(language));
    }

    @Override
    public Optional<LanguageDto> update(LanguageDto entity) {
        Optional<Language> updatedEntity = languageRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            languageRepository.saveAndFlush(updated);
            return Optional.of(mapper.toLanguageDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<LanguageDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Language> updatedEntity = languageRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Language.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toLanguageDto(languageRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return languageRepository.findById(id).map(entity -> {
            languageRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
