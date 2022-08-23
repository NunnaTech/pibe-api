package mx.com.pandadevs.pibeapi.models.resumes;

import mx.com.pandadevs.pibeapi.models.aptitudes.Aptitude;
import mx.com.pandadevs.pibeapi.models.aptitudes.AptitudeService;
import mx.com.pandadevs.pibeapi.models.aptitudes.mapper.AptitudeMapper;
import mx.com.pandadevs.pibeapi.models.certifications.Certification;
import mx.com.pandadevs.pibeapi.models.certifications.CertificationService;
import mx.com.pandadevs.pibeapi.models.certifications.dto.CertificationDto;
import mx.com.pandadevs.pibeapi.models.certifications.mapper.CertificationMapper;
import mx.com.pandadevs.pibeapi.models.courses.CourseService;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.languages.services.ResumeLanguageService;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.ProfileService;
import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.resumes.dto.ResumeDto;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import mx.com.pandadevs.pibeapi.models.studies.StudyService;
import mx.com.pandadevs.pibeapi.models.studies.dto.StudyDto;
import mx.com.pandadevs.pibeapi.models.styles.StyleService;
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;
import mx.com.pandadevs.pibeapi.models.styles.mapper.StyleMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.vacants.controller.UserVacantContoller;
import mx.com.pandadevs.pibeapi.models.work_experiences.WorkExperienceService;
import mx.com.pandadevs.pibeapi.models.work_experiences.dto.WorkExperienceDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ResumeService {
    private final ResumeMapper mapper;
    private final ProfileMapper profileMapper;
    private final StyleMapper styleMapper;

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private CertificationService certificationService;
    @Autowired
    private WorkExperienceService workExperienceService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AptitudeService aptitudeService;
    @Autowired
    private ResumeLanguageService languageService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StyleService styleService;
    public ResumeService(ResumeMapper mapper, ProfileMapper profileMapper, StyleMapper styleMapper) {
        this.mapper = mapper;
        this.profileMapper = profileMapper;
        this.styleMapper = styleMapper;
    }
    @Transactional(readOnly = true)

    public List<ResumeDto> getAll() {
        return mapper.toResumesDto(resumeRepository.findAll());
    }
    public void saveFirstResume(Long profileId) {
        resumeRepository.saveFirstResume(profileId);
    }
    @Transactional(readOnly = true)

    public Optional<ResumeDto> getById(Integer id) {
        Optional<Resume> resume  = resumeRepository.findById(id);
        return resume.map(entity -> {
            return Optional.of(mapper.toResumeDto(entity));
        }).orElse(Optional.empty());
    }
    @Transactional(readOnly = true)
    public Optional<ResumeDto> getByUsername(String username) {
        Optional<Resume> resume = resumeRepository.findResumeByProfileUserUsernameAndActiveTrue(username);
        if(resume.isPresent()){
            // Languages
            resume.get().setLanguages(languageService.getAllByResume(resume.get().getId()));
            // Studies
            resume.get().setStudies(studyService.getAllByResume(resume.get().getId()));
            // Courses
            resume.get().setCourses(courseService.getAllByResume(resume.get().getId()));
            // Work Experiences
            resume.get().setExperiences(workExperienceService.getAllByResume(resume.get().getId()));
            // Certifications
            resume.get().setCertifications(certificationService.getAllByResume(resume.get().getId()));
            return Optional.of(mapper.toResumeDto(resume.get()));
        }
        return Optional.empty();
    }
    public Optional<ResumeDto> changeStyle(String username,Integer styleId) {
        Optional<StyleDto> style = styleService.getById(styleId);
        Optional<ResumeDto> resumeDto = getByUsername(username);
        if(style.isPresent() && resumeDto.isPresent()){
            Optional<Resume> resume = resumeRepository.findById(resumeDto.get().getId());
            resume.get().setStyle(styleService.getStyleById(styleId).get());
            return Optional.ofNullable(mapper.toResumeDto(resumeRepository.save(resume.get())));
        }
        return Optional.empty();
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

    public Optional<ResumeDto> update(ResumeDto entity, ProfileDto profileDto, String username) {

        if(entity.getId() == null) {
            if(checkExistence(entity.getProfile().getId()))
                entity.setId(save(entity).getId());
            else return Optional.empty();
        }
        // Profile
        Profile profile = profileService.getProfileById(profileDto.getId()).get();
        Long userId = profile.getUser().getId();
        // Models
        List<CertificationDto> certifications = entity.getCertifications();
        List<WorkExperienceDto> experiences = entity.getExperiences();
        List<CourseDto> courses = entity.getCourses();
        List<StudyDto> studies = entity.getStudies();

        Optional<Resume> updatedEntity = resumeRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            // New Object
            Resume resume = new Resume(
              entity.getId(),
              entity.getCurricularTitle(),
              entity.getDescription(),
              entity.getCompleted(),
              entity.getActive(),
              profileMapper.toProfile(entity.getProfile()),
              styleMapper.toStyle(entity.getStyle())
            );
            /// Save languages
            languageService.save(entity.getLanguages(),entity);
            // check repeated Aptitudes
            entity.setAptitudes(aptitudeService.checkNames(entity.getAptitudes()));
            // set blank
            entity.setLanguages(new ArrayList<>());
            entity.setCertifications(new ArrayList<>());
            entity.setExperiences(new ArrayList<>());
            entity.setCourses(new ArrayList<>());
            entity.setStudies(new ArrayList<>());

            // Save Resume
            resumeRepository.save(mapper.toResume(entity));
            // Patch Update
            profileService.updatePatch(userId,profile.getId());
            // Patch Certifications
            certificationService.saveInResume(certifications, updated);
            // Patch Work Experiences
            workExperienceService.saveInResume(experiences, updated);
            // Patch Courses
            courseService.saveInResume(courses,updated);
            // Patch Studies
            studyService.saveInResume(studies,updated);

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