package mx.com.pandadevs.pibeapi.models.styles.mapper;
// Mappers
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
// Models
import mx.com.pandadevs.pibeapi.models.styles.Style;
import mx.com.pandadevs.pibeapi.models.styles.dto.StyleDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StyleMapper {

    StyleMapper MAPPER = Mappers.getMapper( StyleMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
    })
    StyleDto toStyleDto(Style style);
    List<StyleDto> toStylesDto(List<Style> styles);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "resumes", ignore = true),
    })
    Style toStyle(StyleDto styleDto);

}
