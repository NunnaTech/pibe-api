package mx.com.pandadevs.pibeapi.models.auth;

import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.auth.common.AuthResponse;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.ProfileRepository;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuth jwtAuth;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        System.out.println(user.toString());
        if (user.getId() == null){
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
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(request.getUsername());
        // If we can find the email return Optional.empty
        if (!user.isPresent()) return response;


        // Check if the encrypted password is the same as the password they sent
        Boolean isMatch = passwordEncoder.matches(request.getPassword(), user.get().getPassword());
        if (!isMatch) return response;


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), user.get().getPassword()));
        UserDetails userDetails = loadUserByUsername(request.getUsername());
        System.out.println("llego:" +userDetails.getUsername());
        System.out.println("llego:" +userDetails.getPassword());
        String jwt = jwtAuth.createToken(userDetails);

        response = Optional.of(new AuthResponse(userService.getByUsername(userDetails.getUsername()),jwt));
        return  response;
    }
}
