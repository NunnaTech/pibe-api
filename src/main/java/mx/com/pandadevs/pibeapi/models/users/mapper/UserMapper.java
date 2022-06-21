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
        @Mapping(source = "id_user", target = "id_user"),
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
