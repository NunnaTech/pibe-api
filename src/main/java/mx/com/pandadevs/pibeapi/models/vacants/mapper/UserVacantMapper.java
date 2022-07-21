package mx.com.pandadevs.pibeapi.models.vacants.mapper;

import mx.com.pandadevs.pibeapi.models.processes.mapper.ProcessMapper;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import mx.com.pandadevs.pibeapi.models.vacants.dto.UserVacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                ProcessMapper.class,
                VacantMapper.class,
                UserMapper.class
        }
)
public interface UserVacantMapper {

    UserVacantMapper MAPPER = Mappers.getMapper(UserVacantMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "vacant", target = "vacant"),
            @Mapping(source = "process", target = "process")
    })
    UserVacantDto toUserVacantDto(UserVacant userVacant);

    List<UserVacantDto> toUserVacantsDto(List<UserVacant> userVacants);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "user", ignore = true)
    })
    UserVacant toUserVacant(UserVacantDto userVacantDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "process", target = "process")
    })
    VacantProcessDto toVacantProcessDto (UserVacant userVacant);

    List<VacantProcessDto> toVacantsProcessDto (List<UserVacant> userVacants);
}
