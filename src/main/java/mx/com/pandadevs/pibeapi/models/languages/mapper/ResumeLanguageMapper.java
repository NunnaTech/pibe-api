package mx.com.pandadevs.pibeapi.models.languages.mapper;
// Java

import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.resumes.mapper.ResumeMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {LanguageMapper.class, ResumeMapper.class})
public interface ResumeLanguageMapper {

    ResumeLanguageMapper MAPPER = Mappers.getMapper( ResumeLanguageMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "language", target = "language"),
        @Mapping(source = "active", target = "active"),
        @Mapping(source = "level", target = "level"),

    })
    ResumeLanguageDto toResumeLanguageDto(ResumeLanguage resumeLanguage);


    List<ResumeLanguageDto> toResumeLanguagesDto(List<ResumeLanguage> resumeLanguages);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "resume", ignore = true)
    })
    ResumeLanguage toResumeLanguage(ResumeLanguageDto languageDto);
    List<ResumeLanguage> toResumeLanguages(List<ResumeLanguageDto> resumeLanguages);

}
