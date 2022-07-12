package mx.com.pandadevs.pibeapi.models.studies.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.studies.Study;
import mx.com.pandadevs.pibeapi.models.studies.dto.StudyDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyMapper {

    StudyMapper MAPPER = Mappers.getMapper( StudyMapper.class);
    
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "startPeriod", target = "startPeriod"),
            @Mapping(source = "endPeriod", target = "endPeriod"),
    })
    StudyDto toStudyDto(Study study);
    List<StudyDto> toStudiesDto(List<Study> studies);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "resume", ignore = true),
    })
    Study toStudy(StudyDto studyDto);

}
