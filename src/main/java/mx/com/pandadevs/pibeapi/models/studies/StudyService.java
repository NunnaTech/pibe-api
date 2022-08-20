package mx.com.pandadevs.pibeapi.models.studies;

import mx.com.pandadevs.pibeapi.models.courses.Course;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;
import mx.com.pandadevs.pibeapi.models.studies.dto.StudyDto;
import mx.com.pandadevs.pibeapi.models.studies.mapper.StudyMapper;
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
public class StudyService implements ServiceInterface<Integer, StudyDto> {
    private  final StudyMapper mapper;
    @Autowired
    private StudyRepository studyRepository;

    public StudyService(StudyMapper mapper){this.mapper = mapper;}

    @Override
    public List<StudyDto> getAll() {
        return mapper.toStudiesDto(studyRepository.findAll());
    }

    @Override
    public Optional<StudyDto> getById(Integer id) {
        Optional<Study> study = studyRepository.findById(id);
        return study.map(entity -> {
            return Optional.of(mapper.toStudyDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public StudyDto save(StudyDto entity) {
        Study study = mapper.toStudy(entity);
        return mapper.toStudyDto(studyRepository.saveAndFlush(study));
    }
    public void saveInResume(List<StudyDto> studies, Resume resume) {
        List<Study> cast = new ArrayList<>();
        for (StudyDto entity: studies) {
            Study saved = mapper.toStudy(entity);
            saved.setResume(resume);
            if(saved.getId() == null) saved = studyRepository.save(saved);
            cast.add(saved);
        }
        studyRepository.saveAll(cast);
    }
    @Override
    public Optional<StudyDto> update(StudyDto entity) {
        Optional<Study> updatedEntity = studyRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            studyRepository.saveAndFlush(updated);
            return Optional.of(mapper.toStudyDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<StudyDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Study> updatedEntity = studyRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Study.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toStudyDto(studyRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return studyRepository.findById(id).map(entity -> {
            studyRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
