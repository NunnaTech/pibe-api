package mx.com.pandadevs.pibeapi.models.languages.services;
// Java
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// Models
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguageFK;
import mx.com.pandadevs.pibeapi.models.languages.mapper.ResumeLanguageMapper;
import mx.com.pandadevs.pibeapi.models.languages.repository.ResumeLanguageRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class ResumeLanguageService implements ServiceInterface<Integer, ResumeLanguageDto> {
    private final ResumeLanguageMapper mapper;
    @Autowired
    private ResumeLanguageRepository languageRepository;

    public ResumeLanguageService(ResumeLanguageMapper mapper){ this.mapper = mapper;}
    @Override
    public List<ResumeLanguageDto> getAll() {
        return mapper.toResumeLanguagesDto(languageRepository.findAll());
    }

    @Override
    public Optional<ResumeLanguageDto> getById(Integer id) {
        Optional<ResumeLanguage> resumeLanguage = languageRepository.findById(id);
        return resumeLanguage.map(entity -> {
            return Optional.of(mapper.toResumeLanguageDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public ResumeLanguageDto save(ResumeLanguageDto entity) {
        ResumeLanguage language = mapper.toResumeLanguage(entity);
        return mapper.toResumeLanguageDto(languageRepository.saveAndFlush(language));
    }

    @Override
    public Optional<ResumeLanguageDto> update(ResumeLanguageDto entity) {
       return null;
    }

    @Override
    public Optional<ResumeLanguageDto> partialUpdate(Integer id, Map<Object, Object> fields) {
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
