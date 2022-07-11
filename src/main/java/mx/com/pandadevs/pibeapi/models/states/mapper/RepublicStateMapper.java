package mx.com.pandadevs.pibeapi.models.states.mapper;

import java.util.List;

import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import mx.com.pandadevs.pibeapi.models.states.RepublicState;

@Mapper(componentModel = "spring")
public interface RepublicStateMapper {

    RepublicStateMapper MAPPER = Mappers.getMapper(RepublicStateMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "name")
    })
    RepublicStateDto toRepublicStateDTO(RepublicState republicState);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "vacants", ignore = true),
            @Mapping(target = "profiles", ignore = true)
    })
    RepublicState toRepublicState(RepublicStateDto republicState);

    List<RepublicStateDto> toRepublicStatesDto(List<RepublicState> states);
}
