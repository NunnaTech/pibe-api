package mx.com.pandadevs.pibeapi.models.auth;

import java.util.ArrayList;
import java.util.Optional;

import mx.com.pandadevs.pibeapi.models.roles.Role;
import mx.com.pandadevs.pibeapi.models.roles.RoleRepository;
import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;

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

    public String register(AuthRequest request){
        ArrayList<Role> list = new ArrayList<>();
        Optional<Role> rol = roleRepository.findById(request.getRoleId());
        if (!rol.isPresent()) return  "El rol seleccionado no existe";
        User user = userRepository.findByUsername(request.getUsername());

        if (user != null){
            if (user.getEmail().equals(request.getEmail()) || user.getUsername().equals(request.getUsername())){
                return "Correo o usuario ya registrados";
            }
        }else{
            User user1 = new User(request.getEmail(), request.getUsername(), passwordEncoder.encode(request.getPassword()));
            list.add(rol.get());
            user1.setActive(false);
            user1.setRoles(list);
            emailService.sendActivationCode(user1);
        }
        return  "registrado correctamente";
    }
}
