package mx.com.pandadevs.pibeapi.models.auth;

import com.sendgrid.Email;
import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Value("${secretGrid}")
    private String sendGridKey;

    @Value("${pibeAccount}")
    private String pibeAccount;

    @Value("${pibePassword}")
    private String pibePassword;

    @Autowired
    private UserRepository userRepository;

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

    public boolean resetPassword (AuthRequest request){
        Email from = new Email(pibeAccount);

        return  true;
    }
}
