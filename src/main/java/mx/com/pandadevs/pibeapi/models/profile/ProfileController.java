package mx.com.pandadevs.pibeapi.models.profile;

import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{username}/profile")
    public ResponseEntity<ProfileDto> getProfileByUsername(@PathVariable("username") String username){
        return profileService.getByUsername(username)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{username}/profile")
    public ResponseEntity<ProfileDto> saveProfileByUsername(@PathVariable("username") String username,@RequestBody ProfileDto request){
        return new ResponseEntity<>(profileService.saveAndSetProfile(username, request), HttpStatus.OK);
    }

    @PutMapping("/{username}/profile")
    public ResponseEntity<ProfileDto> updateProfileByUsername(){
        return new ResponseEntity<>(new ProfileDto(), HttpStatus.OK);
    }
}
