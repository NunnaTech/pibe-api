package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Mapper
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;

// Models
import mx.com.pandadevs.pibeapi.models.users.dto.UserDTO;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
@Service
public class UserService implements ServiceInterface<UserDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDTO> getAll() {
        return mapper.toUsersDTO(userRepository.findAll());
    }

    @Override
    public Optional<UserDTO> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(entity -> {
            return Optional.of(mapper.toUserDTO(entity));
        }).orElse(Optional.empty());
    }

    @Override
    public UserDTO save(UserDTO entity) {
        User user = mapper.toUser(entity);
        return mapper.toUserDTO(userRepository.saveAndFlush(user));
    }

    @Override
    public Optional<UserDTO> update(UserDTO entity) {
        User user = mapper.toUser(entity);
        Optional<User> updatedEntity = userRepository.findById(user.getId());
        return updatedEntity.map(updated -> {
            userRepository.saveAndFlush(updated);
            return Optional.of(mapper.toUserDTO(updated));
        }).orElse(Optional.empty());
    }

    @Override
    public Optional<UserDTO> partialUpdate(Long id, Map<Object, Object> fields) {
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
                return Optional.of(mapper.toUserDTO(updated));
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
