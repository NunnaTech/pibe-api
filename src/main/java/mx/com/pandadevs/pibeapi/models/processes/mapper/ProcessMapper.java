package mx.com.pandadevs.pibeapi.models.processes.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProcessMapper {

    ProcessMapper MAPPER = Mappers.getMapper( ProcessMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
    })
    ProcessDto toProcessDto(Process process);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "userVacants", ignore = true)
    })
    Process toProcess(ProcessDto processDto);

}
