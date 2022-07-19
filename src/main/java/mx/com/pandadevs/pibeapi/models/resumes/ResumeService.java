package mx.com.pandadevs.pibeapi.models.resumes;

import mx.com.pandadevs.pibeapi.models.aptitudes.AptitudeService;
import mx.com.pandadevs.pibeapi.models.languages.services.ResumeLanguageService;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.ProfileService;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ResumeService implements ServiceInterface<Integer,ResumeDto> {
    private final ResumeMapper mapper;
    private final ProfileMapper profileMapper;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private AptitudeService aptitudeService;
    @Autowired
    private ResumeLanguageService languageService;
    @Autowired
    private ProfileService profileService;

    public ResumeService(ResumeMapper mapper, ProfileMapper profileMapper) {
        this.mapper = mapper;
        this.profileMapper = profileMapper;
    }

    @Override
    public List<ResumeDto> getAll() {
        return mapper.toResumesDto(resumeRepository.findAll());
    }

    @Override
    public Optional<ResumeDto> getById(Integer id) {
        Optional<Resume> resume  = resumeRepository.findById(id);
        return resume.map(entity -> {
            return Optional.of(mapper.toResumeDto(entity));
        }).orElse(Optional.empty());
    }

    public Optional<ResumeDto> getByUsername(String username) {
        Optional<Resume> resume = resumeRepository.findResumeByProfileUserUsernameAndActiveTrue(username);
        return resume.map(entity ->{return Optional.of(mapper.toResumeDto(entity));}).orElse(Optional.empty());
    }


    @Override
    public ResumeDto save(ResumeDto entity) {
        Optional<Profile> profileOptional = profileService.getProfileById(entity.getProfile().getId());
        if (profileOptional.isEmpty()) return null;
        Resume resume = new Resume(
                entity.getCurricularTitle(),
                entity.getDescription(),
                entity.getActive(),
                entity.getCompleted(),
                profileOptional.get()
        );
        return mapper.toResumeDto(resumeRepository.save(resume));
    }

    @Override
    public Optional<ResumeDto> update(ResumeDto entity) {
        if(entity.getId() == null) {
            entity.setId(save(entity).getId());
        }
        Optional<Resume> updatedEntity = resumeRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            // Save Aptitudes
            entity.setAptitudes(aptitudeService.save(entity.getAptitudes()));
            /// Save languages
            languageService.save(entity.getLanguages(),entity);
            entity.setLanguages(new ArrayList<>());
            return Optional.of(mapper.toResumeDto(
                    resumeRepository.save(
                            mapper.toResume(entity))));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<ResumeDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Resume> updatedEntity = resumeRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Resume.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toResumeDto(resumeRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return resumeRepository.findResumeByIdAndActiveTrue(id).map(entity -> {
            entity.setActive(false);
            resumeRepository.save(entity);
            return true;
        }).orElse(false);
    }
}