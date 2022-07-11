package mx.com.pandadevs.pibeapi.models.processes.mapper;
import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    List<ProcessDto> toProcessesDto(List<Process> processes);

}
