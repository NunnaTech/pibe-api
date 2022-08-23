package mx.com.pandadevs.pibeapi.models.profile.mapper;
// Java

import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.states.mapper.RepublicStateMapper;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RepublicStateMapper.class, UserMapper.class})
public interface ProfileMapper {

    ProfileMapper MAPPER = Mappers.getMapper( ProfileMapper.class );
    
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "firstName", target = "firstName"),
        @Mapping(source = "secondName", target = "secondName"),
        @Mapping(source = "birthDate", target = "birthDate"),
        @Mapping(source = "image", target = "image"),
        @Mapping(source = "phoneNumber", target = "phoneNumber"),
        @Mapping(source = "completed", target = "completed"),
        @Mapping(source = "gender", target = "gender"),
        @Mapping(source = "state", target = "state"),
        @Mapping(source = "position", target = "position"),
    })
    ProfileDto toProfileDto(Profile profile);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "resumes", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    Profile toProfile(ProfileDto profileDTO);

    List<ProfileDto> toProfilesDto(List<Profile> users);

}
