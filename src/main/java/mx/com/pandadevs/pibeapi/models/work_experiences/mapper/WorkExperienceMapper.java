package mx.com.pandadevs.pibeapi.models.work_experiences.mapper;
// Mappers
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
// Models
import mx.com.pandadevs.pibeapi.models.work_experiences.WorkExperience;
import mx.com.pandadevs.pibeapi.models.work_experiences.dto.WorkExperienceDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkExperienceMapper {

    WorkExperienceMapper MAPPER = Mappers.getMapper( WorkExperienceMapper.class);
    
    @Mappings({
        @Mapping(source = "position", target = "position"),
        @Mapping(source = "startPeriod", target = "startPeriod"),
        @Mapping(source = "endPeriod", target = "endPeriod"),
        @Mapping(source = "activities", target = "activities"),
    })
    WorkExperienceDto toWorkExperienceDto(WorkExperience workExperience);
    List<WorkExperienceDto> toWorkExperiencesDto(List<WorkExperience> workExperiences);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "resume", ignore = true)
    })
    WorkExperience toWorkExperience(WorkExperienceDto workExperienceDto);

}
