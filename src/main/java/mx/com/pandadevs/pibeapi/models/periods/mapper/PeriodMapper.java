package mx.com.pandadevs.pibeapi.models.periods.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import mx.com.pandadevs.pibeapi.models.periods.Period;
import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeriodMapper {

    PeriodMapper MAPPER = Mappers.getMapper( PeriodMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
    })
    PeriodDto toPeriodDto(Period period);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "vacants", ignore = true)
    })
    Period toPeriod(PeriodDto periodDto);

    List<PeriodDto> toPeriodsDto(List<Period> periods);

}
