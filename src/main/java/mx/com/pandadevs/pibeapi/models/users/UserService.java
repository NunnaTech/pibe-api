package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.*;
import java.lang.reflect.Field;

import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.services.UserNotificationService;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Mapper
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;

// Models
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
@Service
public class UserService implements ServiceInterface<Long,UserDto> {


    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNotificationService notificationService;

    public UserService(UserMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> getAll() {
        return mapper.toUsersDto(userRepository.findAll());
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(mapper.toUserDto(entity));
        }).orElse(Optional.empty());
    }

    public Optional<UserProfileDto> getByProfileById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(mapper.toUserProfileDto(entity));
        }).orElse(Optional.empty());
    }

    public Optional<UserProfileDto> getByUsername(String username) {
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        return user.map(entity -> {
            return Optional.of(mapper.toUserProfileDto(entity));
        }).orElse(Optional.empty());
    }

    public UserDto getUserByUsername(String username){
        return mapper.toUserDto(userRepository.findByUsernameAndActiveTrue(username).get());
    }

    public Profile getProfileByUsername(String username) {
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        logger.error(user.get().getProfile().getFirstName());
        return user.map(User::getProfile).orElse(null);
    }


    public List<UserNotificationDto> getNotificationsByUsername(String username) {
        return notificationService.getAllByUser(username);
    }

    @Override
    public UserDto save(UserDto entity) {
        User user = mapper.toUser(entity);
        return mapper.toUserDto(userRepository.saveAndFlush(user));
    }

    @Override
    public Optional<UserDto> update(UserDto entity) {
        User user = mapper.toUser(entity);
        Optional<User> updatedEntity = userRepository.findById(user.getId());
        return updatedEntity.map(updated -> {
            userRepository.saveAndFlush(updated);
            return Optional.of(mapper.toUserDto(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<UserDto> partialUpdate(Long id, Map<Object, Object> fields) {
        Optional<User> updatedEntity = Optional.empty();
        try {
            updatedEntity = userRepository.findById(id);
            return updatedEntity.map(updated -> {
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(User.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, updated, value);
                });
                userRepository.saveAndFlush(updated);
                return Optional.of(mapper.toUserDto(updated));
            }).orElse(Optional.empty());
        } catch (Exception exception) {
            
        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return userRepository.findById(id).map(entity -> {
            userRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
