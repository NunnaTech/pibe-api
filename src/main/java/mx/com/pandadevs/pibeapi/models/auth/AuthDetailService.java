package mx.com.pandadevs.pibeapi.models.auth;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.auth.common.AuthResponse;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.security.JwtAuth;

@Service
public class AuthDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuth jwtAuth;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), "{noop}"+user.get().getPassword(), new ArrayList<>());
    }

    public Optional<AuthResponse> login(AuthRequest request) {
        Optional<AuthResponse> response = Optional.empty();
        Optional<User> user = userRepository.findByUsernameAndActiveTrue(request.getUsername());
        if (!user.isPresent()) return response;

        Boolean isMatch = passwordEncoder.matches(request.getPassword(), user.get().getPassword());
        if (!isMatch) return response;

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), user.get().getPassword()));
        UserDetails userDetails = loadUserByUsername(request.getUsername());
        String jwt = jwtAuth.createToken(userDetails);

        response = Optional.of(new AuthResponse(userService.getByUsername(userDetails.getUsername()),jwt));
        return  response;
    }
}
