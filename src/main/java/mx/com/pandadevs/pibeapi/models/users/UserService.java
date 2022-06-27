package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;

// Spring
import mx.com.pandadevs.pibeapi.models.profile.mapper.ProfileMapper;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Mapper
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;

// Models
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
@Service
public class UserService implements ServiceInterface<UserDto> {

    private  final UserMapper mapper;
    private  final ProfileMapper profileMapper;
    @Autowired
    private UserRepository userRepository;

    public UserService(UserMapper mapper, ProfileMapper profileMapper){
        this.mapper = mapper;
        this.profileMapper = profileMapper;
    }

    @Override
    public List<UserDto> getAll() {
        return mapper.toUsersDto(userRepository.findAll());
    }

    public List<User> get() {
        return userRepository.findAll();
    }
    @Override
    public Optional<UserDto> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(mapper.toUserDto(entity));
        }).orElse(Optional.empty());
    }

    public Optional<UserProfileDto> getByUsername(String username) {
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        return user.map(entity -> {
            return Optional.of(mapper.toUserProfileDto(entity));
        }).orElse(Optional.empty());
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
