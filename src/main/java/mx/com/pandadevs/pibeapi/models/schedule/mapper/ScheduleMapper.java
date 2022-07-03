package mx.com.pandadevs.pibeapi.models.schedule.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.schedule.Schedule;
import mx.com.pandadevs.pibeapi.models.schedule.dto.ScheduleDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleMapper MAPPER = Mappers.getMapper( ScheduleMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
    })
    ScheduleDto toScheduleDto(Schedule schedule);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "vacants", ignore = true)
    })
    Schedule toSchedule(ScheduleDto scheduleDto);

}
