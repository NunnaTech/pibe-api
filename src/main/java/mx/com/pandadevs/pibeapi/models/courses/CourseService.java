package mx.com.pandadevs.pibeapi.models.courses;

import mx.com.pandadevs.pibeapi.models.aptitudes.Aptitude;
import mx.com.pandadevs.pibeapi.models.aptitudes.AptitudeRepository;
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.aptitudes.mapper.AptitudeMapper;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.courses.mapper.CourseMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                    Field field = ReflectionUtils.findField(User.class, (String) updatedfield);
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
