package mx.com.pandadevs.pibeapi.models.auth;

import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.roles.Role;
import mx.com.pandadevs.pibeapi.models.roles.RoleRepository;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean changePassword(AuthRequest request){

        Optional<User> user =  userRepository.findByUsernameAndActiveTrue(request.getUsername());
        if (!user.isPresent() || !user.get().getActive()) return  false;

        boolean match = passwordEncoder.matches(request.getCurrentPassword(), user.get().getPassword());
        if (!match) return false;

        user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user.get());
        return true;
    }

    public boolean resetPassword(AuthRequest request){
        String key = request.getKey();
        String newPassword = request.getNewPassword();
        Optional<User> user = userRepository.findByLinkRestorePasswordAndActiveTrue(key);
        if (!user.isPresent()) return  false;

        user.get().setPassword(passwordEncoder.encode(newPassword));
        user.get().setLinkRestorePassword(null);
        userRepository.save(user.get());
        return true;
    }

    public Optional<String> register(AuthRequest request){
        ArrayList<Role> list = new ArrayList<>();
        Optional<Role> rol = roleRepository.findById(request.getRoleId());
        if (!rol.isPresent()) return  Optional.empty();
        List<User> user = userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (user.size()>0){
                return Optional.of("Correo o usuario ya registrados");
        }else{
            User user1 = new User(request.getEmail(), request.getUsername(), passwordEncoder.encode(request.getPassword()));
            list.add(rol.get());
            user1.setActive(true);
            user1.setRoles(list);
            emailService.sendEmailNewAccount(user1);
            userRepository.save(user1);
        }
        return  Optional.of("registrado correctamente");
    }
}
