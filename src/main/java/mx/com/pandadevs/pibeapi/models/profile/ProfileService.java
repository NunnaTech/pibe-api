package mx.com.pandadevs.pibeapi.models.profile;

import mx.com.pandadevs.pibeapi.models.profile.dto.ProfileDto;
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.resumes.ResumeService;
import mx.com.pandadevs.pibeapi.models.states.mapper.RepublicStateMapper;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfileService implements ServiceInterface<Long, ProfileDto> {
    private Logger logger = LoggerFactory.getLogger(ProfileService.class);

    private final ProfileMapper mapper;

    private final RepublicStateMapper republicStateMapper;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileService(ProfileMapper profileMapper, RepublicStateMapper republicStateMapper) {
        this.mapper = profileMapper;
        this.republicStateMapper = republicStateMapper;
    }

    @Override
    public List<ProfileDto> getAll() {
        return mapper.toProfilesDto(profileRepository.findAll());
    }

    public Optional<ProfileDto> getByUsername(String username) {
        Optional<Profile> profile = profileRepository.findByUserUsernameAndUserActiveTrue(username);
        return profile.map(entity -> {
            return Optional.of(mapper.toProfileDto(entity));
        }).orElse(Optional.empty());
    }


    @Override
    public Optional<ProfileDto> getById(Long id) {
        Optional<Profile> user = profileRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(mapper.toProfileDto(entity));
        }).orElse(Optional.empty());
    }

    public Optional<Profile> getProfileById(Long id) {
        Optional<Profile> user = profileRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(entity);
        }).orElse(Optional.empty());
    }

    @Override
    public ProfileDto save(ProfileDto entity) {
        Profile user = mapper.toProfile(entity);
        return mapper.toProfileDto(profileRepository.saveAndFlush(user));
    }

    public ProfileDto saveAndSetProfile(String username, ProfileDto request) {
        Profile profile = mapper.toProfile(request);
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        if (!user.isPresent()) return null;
        user.get().setProfile(profile);
        profile.setUser(user.get());
        userRepository.save(user.get());
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

    @Transactional
    public Optional<ProfileDto> update(String username, ProfileDto entity) {
        try {
            Optional<Profile> updatedEntity = profileRepository.findById(entity.getId());
            if (updatedEntity.isPresent()) {
                if (username.equals(updatedEntity.get().getUser().getUsername())) {
                    logger.error(updatedEntity.get().getId().toString());
                    Profile profile = mapper.toProfile(entity);
                    updatedEntity.get().setName(profile.getName());
                    updatedEntity.get().setFirstName(profile.getFirstName());
                    updatedEntity.get().setSecondName(profile.getSecondName());
                    updatedEntity.get().setBirthDate(profile.getBirthDate());
                    updatedEntity.get().setGender(profile.getGender());
                    updatedEntity.get().setCompleted(profile.getCompleted());
                    updatedEntity.get().setImage(profile.getImage());
                    updatedEntity.get().setPhoneNumber(profile.getPhoneNumber());
                    updatedEntity.get().setState(profile.getState());
                    updatedEntity.get().setPosition(profile.getPosition());
                    return updatedEntity.map(updated -> mapper.toProfileDto(profileRepository.save(updatedEntity.get())));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.toString());
        }
        return Optional.empty();
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
        Optional<Profile> updatedEntity;
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
