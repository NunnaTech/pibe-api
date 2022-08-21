package mx.com.pandadevs.pibeapi.models.work_experiences;

// Java
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Spring
import mx.com.pandadevs.pibeapi.models.courses.Course;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Models
import mx.com.pandadevs.pibeapi.models.work_experiences.dto.WorkExperienceDto;
import mx.com.pandadevs.pibeapi.models.work_experiences.mapper.WorkExperienceMapper;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;

@Service
public class WorkExperienceService implements ServiceInterface<Integer, WorkExperienceDto> {
    private final WorkExperienceMapper mapper;

    public WorkExperienceService(WorkExperienceMapper mapper){ this.mapper = mapper;}
    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Override
    public List<WorkExperienceDto> getAll() {
        return mapper.toWorkExperiencesDto(workExperienceRepository.findAll());
    }
    public List<WorkExperience> getAllByResume(Integer resumeId) {
        return workExperienceRepository.findAllByResumeIdAndActiveTrueOrderByCreatedAtAsc(resumeId);
    }
    @Override
    public Optional<WorkExperienceDto> getById(Integer id) {
        Optional<WorkExperience> workExperience = workExperienceRepository.findById(id);
        return workExperience.map(entity -> {
            return Optional.of(mapper.toWorkExperienceDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public WorkExperienceDto save(WorkExperienceDto entity) {
        WorkExperience workExperience = mapper.toWorkExperience(entity);
        return mapper.toWorkExperienceDto(workExperienceRepository.saveAndFlush(workExperience));
    }
    public void saveInResume(List<WorkExperienceDto> experiences, Resume resume) {
        List<WorkExperience> cast = new ArrayList<>();
        for (WorkExperienceDto entity: experiences) {
            WorkExperience saved = mapper.toWorkExperience(entity);
            saved.setResume(resume);
            if(saved.getId() == null) saved = workExperienceRepository.save(saved);
            cast.add(saved);
        }
        workExperienceRepository.saveAll(cast);
    }
    @Override
    public Optional<WorkExperienceDto> update(WorkExperienceDto entity) {
        Optional<WorkExperience> updatedEntity = workExperienceRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            workExperienceRepository.saveAndFlush(updated);
            return Optional.of(mapper.toWorkExperienceDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<WorkExperienceDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<WorkExperience> updatedEntity = workExperienceRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(WorkExperience.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toWorkExperienceDto(workExperienceRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return workExperienceRepository.findById(id).map(entity -> {
            workExperienceRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
