package mx.com.pandadevs.pibeapi.models.vacants.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.benefits.mapper.BenefitMapper;
import mx.com.pandadevs.pibeapi.models.modes.mapper.ModeMapper;
import mx.com.pandadevs.pibeapi.models.periods.Period;
import mx.com.pandadevs.pibeapi.models.periods.dto.PeriodDto;
import mx.com.pandadevs.pibeapi.models.periods.mapper.PeriodMapper;
import mx.com.pandadevs.pibeapi.models.schedule.mapper.ScheduleMapper;
import mx.com.pandadevs.pibeapi.models.states.mapper.RepublicStateMapper;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {
        ScheduleMapper.class,
        PeriodMapper.class,
        ModeMapper.class,
        BenefitMapper.class,
        UserMapper.class,
        RepublicStateMapper.class
})
public interface VacantMapper {

    VacantMapper MAPPER = Mappers.getMapper( VacantMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "startDate", target = "startDate"),
        @Mapping(source = "endDate", target = "endDate"),
        @Mapping(source = "salary", target = "salary"),
        @Mapping(source = "public", target = "public"),
        @Mapping(source = "image", target = "image"),
        @Mapping(source = "schedule", target = "schedule"),
        @Mapping(source = "period", target = "period"),
        @Mapping(source = "mode", target = "mode"),
        @Mapping(source = "benefits", target = "benefits"),
        @Mapping(source = "state", target = "state"),
        @Mapping(source = "user", target = "creator")
    })
    VacantDto toVacantDto(Vacant vacant);
    List<VacantDto> toVacantsDto(List<Vacant> vacants);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "users", ignore = true),
            @Mapping(target = "userVacants", ignore = true),
    })
    Vacant toVacant(VacantDto vacantDto);

}
