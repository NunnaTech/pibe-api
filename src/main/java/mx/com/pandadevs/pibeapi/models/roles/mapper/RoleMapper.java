package mx.com.pandadevs.pibeapi.models.roles.mapper;
// Mappers
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
// Models
import mx.com.pandadevs.pibeapi.models.roles.Role;
import mx.com.pandadevs.pibeapi.models.roles.dto.RoleDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper MAPPER = Mappers.getMapper( RoleMapper.class);
    @Mappings({
        @Mapping(source = "name", target = "authority"),
        @Mapping(source = "description", target = "description"),
    })
    RoleDto toRoleDto(Role role);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Role toRole(RoleDto roleDto);

    List<RoleDto> toRoleDto(List<Role> roles);

}
