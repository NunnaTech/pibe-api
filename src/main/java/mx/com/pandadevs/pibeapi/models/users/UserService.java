package mx.com.pandadevs.pibeapi.models.users;
// Java
import java.util.*;
import java.lang.reflect.Field;

// Spring
import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.auth.common.AuthResponse;
import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.services.NotificationService;
import mx.com.pandadevs.pibeapi.models.notifications.services.UserNotificationService;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.ProfileRepository;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.models.users.dto.UserProfileDto;
import mx.com.pandadevs.pibeapi.security.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

// Mapper
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;

// Models
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
@Service
public class UserService implements ServiceInterface<UserDto> , UserDetailsService {

    private final UserMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserNotificationService notificationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuth jwtAuth;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserMapper mapper){
        this.mapper = mapper;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
       if (user != null){
           throw new UsernameNotFoundException("User not found");
       }
       Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       user.getRoles().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public Optional<AuthResponse> login(AuthRequest request) {
        Optional<AuthResponse> response = Optional.empty();
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(request.getEmail());
        // If we can find the email return Optional.empty
        if (!user.isPresent()) return response;


        // Check if the encrypted password is the same as the password they sent
        Boolean isMatch = passwordEncoder.matches(request.getPassword(), user.get().getPassword());
        if (!isMatch) return response;


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), user.get().getPassword()));
        UserDetails userDetails = loadUserByUsername(request.getEmail());
        String jwt = jwtAuth.createToken(userDetails);
        Optional<Profile> profile = Optional.of(profileRepository.getById(user.get().getId()));
        response = Optional.of(new AuthResponse(user.get(),profile,jwt));
        return  response;
    }
}
