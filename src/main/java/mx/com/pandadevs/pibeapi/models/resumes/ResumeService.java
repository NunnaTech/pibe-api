package mx.com.pandadevs.pibeapi.models.resumes;

import mx.com.pandadevs.pibeapi.models.aptitudes.AptitudeService;
import mx.com.pandadevs.pibeapi.models.languages.services.ResumeLanguageService;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.ProfileService;
import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.vacants.controller.UserVacantContoller;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ResumeService {
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

    public List<ResumeDto> getAll() {
        return mapper.toResumesDto(resumeRepository.findAll());
    }
    public void saveFirstResume(Long profileId) {
        resumeRepository.saveFirstResume(profileId);
    }

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

    public Optional<ResumeDto> update(ResumeDto entity, ProfileDto profileDto) {

        if(entity.getId() == null) {
            if(checkExistence(entity.getProfile().getId()))
                entity.setId(save(entity).getId());
            else return Optional.empty();
        }
        // Profile
        Profile profile = profileService.getProfileById(profileDto.getId()).get();
        Long userId = profile.getUser().getId();

        Optional<Resume> updatedEntity = resumeRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {

            // Save Aptitudes
            entity.setAptitudes(aptitudeService.save(entity.getAptitudes()));
            /// Save languages
            languageService.save(entity.getLanguages(),entity);
            entity.setLanguages(new ArrayList<>());

            // Save Resume
            resumeRepository.save(mapper.toResume(entity));
            // Patch Update
            profileService.updatePatch(userId,profile.getId());
            return getById(entity.getId());
        }).orElse(Optional.empty());
    }
    public boolean checkExistence(Long id){
        return resumeRepository.findResumeByProfileId(id).isEmpty();
    }

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

    public Boolean delete(Integer id) {
        return resumeRepository.findResumeByIdAndActiveTrue(id).map(entity -> {
            entity.setActive(false);
            resumeRepository.save(entity);
            return true;
        }).orElse(false);
    }
}