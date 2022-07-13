package mx.com.pandadevs.pibeapi.models.profile;

import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ProfileService implements ServiceInterface<Long,ProfileDto> {

    private  final ProfileMapper mapper;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileService(ProfileMapper profileMapper){
        this.mapper = profileMapper;
    }

    @Override
    public List<ProfileDto> getAll() {
        return mapper.toProfilesDto(profileRepository.findAll());
    }

    public Optional<ProfileDto> getByUsername(String username){
        Optional<Profile> profile = profileRepository.findByUserUsernameAndUserActiveTrue(username);
        return  profile.map( entity -> {
            return  Optional.of(mapper.toProfileDto(entity));
        }).orElse(Optional.empty());
    }
    @Override
    public Optional<ProfileDto> getById(Long id) {
        Optional<Profile> user = profileRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(mapper.toProfileDto(entity));
        }).orElse(Optional.empty());
    }
    @Override
    public ProfileDto save(ProfileDto entity) {
        Profile user = mapper.toProfile(entity);
        return mapper.toProfileDto(profileRepository.saveAndFlush(user));
    }

    public  ProfileDto saveAndSetProfile(String username, ProfileDto request){
        Profile profile = mapper.toProfile(request);
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        if (!user.isPresent()) return null;
        profile.setUser(user.get());
        profileRepository.save(profile);
        return mapper.toProfileDto(profile);
    }

    @Override
    public Optional<ProfileDto> update(ProfileDto entity) {
        Profile profile = mapper.toProfile(entity);
        Optional<Profile> updatedEntity = profileRepository.findById(profile.getId());
        return updatedEntity.map(updated -> {
            return Optional.of(mapper.toProfileDto(
                    profileRepository.save(profile)));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<ProfileDto> partialUpdate(Long id, Map<Object, Object> fields) {
        Optional<Profile> updatedEntity = Optional.empty();
        try {
            updatedEntity = profileRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Profile.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                profileRepository.saveAndFlush(updated);
                return Optional.of(mapper.toProfileDto(updated));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    public Optional<ProfileDto> partialUpdate(String username, Map<Object, Object> fields) {
        Optional<Profile> updatedEntity = Optional.empty();
        try {
            updatedEntity = profileRepository.findByUserUsernameAndUserActiveTrue(username);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(Profile.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                profileRepository.saveAndFlush(updated);
                return Optional.of(mapper.toProfileDto(updated));
            }).orElse(Optional.empty());
        } catch (Exception exception) {

        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return profileRepository.findById(id).map(entity -> {
            profileRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
