package mx.com.pandadevs.pibeapi.models.users.mapper;
// Mapper
import org.mapstruct.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
// Models
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
        @Mapping(source = "id_user", target = "id"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "password", target = "password"),
        @Mapping(source = "active", target = "active"),
    })
    User toUser(UserDTO userDTO);
    

    @InheritInverseConfiguration
    @Mapping(target = "linkRestorePassword,linkActivateEmail", ignore = true)
    UserDTO toUserDTO(User user);
    List<UserDTO> toUsersDTO(List<User> users);
}
