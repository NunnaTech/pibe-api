package mx.com.pandadevs.pibeapi.models.benefits.mapper;
// Mappers

import mx.com.pandadevs.pibeapi.models.benefits.Benefit;
import mx.com.pandadevs.pibeapi.models.benefits.dto.BenefitDto;
import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.modes.dto.ModeDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BenefitMapper {

    BenefitMapper MAPPER = Mappers.getMapper( BenefitMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
    })
    BenefitDto toBenefitDto(Benefit benefit);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "vacants", ignore = true)
    })
    Benefit toBenefit(BenefitDto benefitDto);

}
