package mx.com.pandadevs.pibeapi.models.courses.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.courses.Course;
import mx.com.pandadevs.pibeapi.models.courses.dto.CourseDto;
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper MAPPER = Mappers.getMapper( CourseMapper.class);
    
    @Mappings({
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "hours", target = "hours"),
        @Mapping(source = "trainingInstitution", target = "trainingInstitution"),
        @Mapping(source = "realizationDate", target = "realizationDate"),
        @Mapping(source = "finishedDate", target = "finishedDate"),
    })
    CourseDto toCourseDto(Course course);
    List<CourseDto> toCoursesDto(List<Course> courses);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "resume", ignore = true)
    })
    Course toCourse(CourseDto courseDto);

}
