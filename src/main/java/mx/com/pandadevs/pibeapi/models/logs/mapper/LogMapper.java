package mx.com.pandadevs.pibeapi.models.logs.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.logs.dto.LogDto;
import mx.com.pandadevs.pibeapi.models.logs.entities.Log;
import mx.com.pandadevs.pibeapi.models.periods.Period;
import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class,TableMapper.class})
public interface LogMapper {

    LogMapper MAPPER = Mappers.getMapper( LogMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "oldData", target = "oldData"),
        @Mapping(source = "newData", target = "newData"),
        @Mapping(source = "action", target = "action"),
        @Mapping(source = "user", target = "user"),
        @Mapping(source = "table", target = "table"),
    })
    LogDto toLogDto(Log log);
    List<LogDto> toLogsDto(List<Log> logs);
    @InheritInverseConfiguration
    Log toLog(LogDto logDto);

}
