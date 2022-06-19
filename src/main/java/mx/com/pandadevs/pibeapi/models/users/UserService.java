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

// Interface
import mx.com.pandadevs.pibeapi.utils.ServiceInterface;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> update(User entity) {
        Optional<User> updatedEntity = getById(entity.getId());
        if (!updatedEntity.isEmpty())
            userRepository.save(entity);
        return updatedEntity;
    }

    @Override
    public Optional<User> partialUpdate(Long id, Map<Object, Object> fields) {
        Optional<User> updatedEntity = Optional.empty();
        try {
            User entity = getById(id).get();
            if (entity != null) {
                // Map key is field name, v is value
                fields.forEach((updatedfield, value) -> {
                    // use reflection to get fields updatedfield on manager and set it to value updatedfield
                    Field field = ReflectionUtils.findField(User.class, (String) updatedfield);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, entity, value);
                });
                userRepository.save(entity);
                updatedEntity = Optional.of(entity);
            }

        } catch (Exception exception) {
           
        }
        return updatedEntity;
    }

    @Override
    public Boolean delete(Long id) {
        return getById(id).map(entity -> {
            userRepository.delete(entity);
            return true;
        }).orElse(false);

    }
}
