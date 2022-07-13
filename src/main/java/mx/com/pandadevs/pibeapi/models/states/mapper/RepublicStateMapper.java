package mx.com.pandadevs.pibeapi.models.states.mapper;
// Java
import java.util.List;

// Mappers
import mx.com.pandadevs.pibeapi.models.states.dto.RepublicStateDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

// Models
import mx.com.pandadevs.pibeapi.models.states.RepublicState;

@Mapper(componentModel = "spring")
public interface RepublicStateMapper {

    RepublicStateMapper MAPPER = Mappers.getMapper( RepublicStateMapper.class );
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    RepublicStateDto toRepublicStateDTO(RepublicState republicState);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "vacants", ignore = true),
            @Mapping(target = "profiles", ignore = true)
    })
    RepublicState toRepublicState(RepublicStateDto republicState);
}
