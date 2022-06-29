package mx.com.pandadevs.pibeapi.models.languages.mapper;
// Java

import mx.com.pandadevs.pibeapi.models.languages.dto.LanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.Language;
import mx.com.pandadevs.pibeapi.models.notifications.dto.NotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LanguageMapper {

    LanguageMapper MAPPER = Mappers.getMapper( LanguageMapper.class);
    
    @Mappings({
        @Mapping(source = "name", target = "language"),
        @Mapping(source = "abbreviation", target = "abbreviation"),
    })
    LanguageDto toLanguageDto (Language language);

    List<LanguageDto> toLanguagesDto(List<Language> languages);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "resumes", ignore = true)
    })
    Language toLanguage(LanguageDto languageDto);
    List<Language> toLanguages(List<LanguageDto> languagesDto);

}
