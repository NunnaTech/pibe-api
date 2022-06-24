package mx.com.pandadevs.pibeapi.models.users.mapper;
// Java
import java.util.List;
// Mapper
import org.mapstruct.Mapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
// Spring

// Models
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper( UserMapper.class );
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "password", target = "password"),
        @Mapping(source = "active", target = "active"),
    })
    UserDTO toUserDTO(User user);

    @InheritInverseConfiguration
    @Mapping(target = "linkRestorePassword", ignore = true)
    @Mapping(target = "linkActivateEmail", ignore = true)
    User toUser(UserDTO userDTO);

    List<UserDTO> toUsersDTO(List<User> users);
}
