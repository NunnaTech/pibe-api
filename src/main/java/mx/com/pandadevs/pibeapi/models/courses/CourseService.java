package mx.com.pandadevs.pibeapi.models.courses;
// Java
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
// Models
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.courses.mapper.CourseMapper;
import mx.com.pandadevs.pibeapi.models.resumes.Resume;

@Service
public class CourseService implements ServiceInterface<Integer, CourseDto> {
    private final CourseMapper mapper;

    @Autowired
    private CourseRepository courseRepository;

    public CourseService(CourseMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<CourseDto> getAll() {
        return mapper.toCoursesDto(courseRepository.findAll());
    }

    @Override
    public Optional<CourseDto> getById(Integer id) {
        Optional<Course> aptitude = courseRepository.findById(id);
        return aptitude.map(entity -> {
            return Optional.of(mapper.toCourseDto(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public CourseDto save(CourseDto entity) {
        Course course = mapper.toCourse(entity);
        return mapper.toCourseDto(courseRepository.saveAndFlush(course));
    }
    public void saveInResume(List<CourseDto> courses, Resume resume) {
        for (CourseDto entity: courses) {
            Course saved = mapper.toCourse(entity);
            saved.setResume(resume);
            if(entity.getId() == null){
                 courseRepository.save(saved);
            }else{
                courseRepository.updateCourse(
                        saved.getActive(),
                        saved.getFinishedDate(),
                        saved.getHours(),
                        saved.getName(),
                        saved.getRealizationDate(),
                        saved.getTrainingInstitution(),
                        saved.getResume().getId(),
                        saved.getId()
                );
            }
        }
    }
    @Override
    public Optional<CourseDto> update(CourseDto entity) {
        Optional<Course> updatedEntity = courseRepository.findById(entity.getId());
        return updatedEntity.map(updated -> {
            courseRepository.saveAndFlush(updated);
            return Optional.of(mapper.toCourseDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<CourseDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Optional<Course> updatedEntity = courseRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Course.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                return Optional.of(mapper.toCourseDto(courseRepository.saveAndFlush(updated)));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return courseRepository.findById(id).map(entity -> {
            courseRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
