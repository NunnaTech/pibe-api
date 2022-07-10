package mx.com.pandadevs.pibeapi.models.profile.mapper;

import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class shouldMapProfileToDto {
    @Test
    public void shouldMapProfileToDto() {
        //given
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setName("Bruce");
        profile.setSecondName("Wayne");
        //when
        ProfileDto profileDto = ProfileMapper.MAPPER.toProfileDto( profile );

        //then
        assertThat( profileDto ).isNotNull();
        assertThat( profileDto.getName() ).isEqualTo( "Bruce" );
        assertThat( profileDto.getSecondName() ).isEqualTo( "Wayne");
    }
}