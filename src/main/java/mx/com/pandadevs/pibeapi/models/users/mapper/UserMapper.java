package mx.com.pandadevs.pibeapi.models.users.mapper;
// Java
import java.util.List;
// Mapper
import mx.com.pandadevs.pibeapi.models.roles.mapper.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
// Spring

// Models
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;


@Mapper(componentModel = "spring", uses = {ProfileMapper.class, RoleMapper.class})
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper( UserMapper.class);
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "password", target = "password"),
        @Mapping(source = "active", target = "active"),
    })
    UserDto toUserDto(User user);

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "password", target = "password"),
        @Mapping(source = "active", target = "active"),
        @Mapping(source = "profile", target = "profile"),
        @Mapping(source = "roles", target = "authorities"),
    })
    UserProfileDto toUserProfileDto(User user);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "linkRestorePassword", ignore = true),
            @Mapping(target = "linkActivateEmail", ignore = true),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "notifications", ignore = true),
            @Mapping(target = "vacants", ignore = true),
            @Mapping(target = "favoitesVacants", ignore = true),
            @Mapping(target = "userVacants", ignore = true),
            @Mapping(target = "profile", ignore = true),
            @Mapping(target = "logs", ignore = true)
    })
    User toUser(UserDto userDTO);

    List<UserDto> toUsersDto(List<User> users);

}
