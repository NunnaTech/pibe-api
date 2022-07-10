package mx.com.pandadevs.pibeapi.models.modes.mapper;
// Mappers

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

// Models
import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModeMapper {

    ModeMapper MAPPER = Mappers.getMapper(ModeMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
    })
    ModeDto toModeDto(Mode mode);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "vacants", ignore = true)
    })
    Mode toMode(ModeDto modeDto);

    List<ModeDto> toModesDto(List<Mode> modes);

}
