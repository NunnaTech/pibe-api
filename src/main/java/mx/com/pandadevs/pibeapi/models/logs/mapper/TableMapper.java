package mx.com.pandadevs.pibeapi.models.logs.mapper;

import mx.com.pandadevs.pibeapi.models.logs.dto.TableDto;
import mx.com.pandadevs.pibeapi.models.logs.entities.Tables;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {

    TableMapper MAPPER = Mappers.getMapper( TableMapper.class);
    
    @Mappings({
        @Mapping(source = "name", target = "name"),
    })
    TableDto toTableDto(Tables tables);
    List<TableDto> toTablesDto(List<Tables> tables);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "logs", ignore = true)
    })
    Tables toTables(TableDto tableDto);

}
