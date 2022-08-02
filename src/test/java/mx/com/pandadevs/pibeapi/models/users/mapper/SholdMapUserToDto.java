package mx.com.pandadevs.pibeapi.models.users.mapper;

import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.users.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SholdMapUserToDto {
    @Test
    public void SholdMapUserToDto() {
        //given
        User user = new User();
        user.setId(1L);
        Profile profile = new Profile();

        profile.setName("Bruce");
        profile.setSecondName("Wayne");
        user.setProfile(profile);
        //when
        //UserDto profileDto = UserMapper.MAPPER.toUserDTO( user );

        //then
    }
}