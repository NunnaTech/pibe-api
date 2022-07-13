package mx.com.pandadevs.pibeapi.models.languages.mapper;
// Java

import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.notifications.dto.NotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import mx.com.pandadevs.pibeapi.utils.enums.Level;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {LanguageMapper.class})
public interface ResumeLanguageMapper {

    ResumeLanguageMapper MAPPER = Mappers.getMapper( ResumeLanguageMapper.class);
    
    @Mappings({
        @Mapping(source = "language", target = "language"),
        @Mapping(source = "level", target = "level"),
    })
    ResumeLanguageDto toResumeLanguageDto(ResumeLanguage resumeLanguage);
    default Level map(String value) {
        return Level.getLevelByName(value);
    }
    List<ResumeLanguageDto> toResumeLanguagesDto(List<ResumeLanguage> resumeLanguages);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "resume", ignore = true)
    })
    ResumeLanguage toResumeLanguage(ResumeLanguageDto languageDto);

}
