package mx.com.pandadevs.pibeapi.models.aptitudes.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.aptitudes.Aptitude;
import mx.com.pandadevs.pibeapi.models.aptitudes.dto.AptitudeDto;
import mx.com.pandadevs.pibeapi.models.languages.dto.ResumeLanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.ResumeLanguage;
import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AptitudeMapper {

    AptitudeMapper MAPPER = Mappers.getMapper( AptitudeMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
    })
    AptitudeDto toAptitudeDto(Aptitude aptitude);
    List<AptitudeDto> toAptitudesDto(List<Aptitude> aptitudes);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "resumes", ignore = true),
    })
    Aptitude toAptitude(AptitudeDto aptitudeDto);

}
