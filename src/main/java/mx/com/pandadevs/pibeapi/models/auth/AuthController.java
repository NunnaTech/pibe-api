package mx.com.pandadevs.pibeapi.models.auth;

import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.auth.common.AuthResponse;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthDetailService authDetailService;

    @Autowired
    private EmailService emailService;

    @Autowired AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return authDetailService.login(request)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody AuthRequest request){
        return  new ResponseEntity<>(authService.changePassword(request), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody AuthRequest request){
        return  new ResponseEntity<>(emailService.sendEmailPasswordRecovery(request), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody AuthRequest request){
        return  new ResponseEntity<>(authService.resetPassword(request),HttpStatus.OK);
    }
}
