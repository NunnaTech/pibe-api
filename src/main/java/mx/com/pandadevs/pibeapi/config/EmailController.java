package mx.com.pandadevs.pibeapi.config;

import mx.com.pandadevs.pibeapi.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/")
    public void sendEmail() {
        User user = new User();
        user.setEmail("20193tn070@utez.edu.mx");
        if (emailService.sendEmailPasswordRecovery(user))
            System.out.println("enviado");
        else
            System.out.println("no enviado");
    }
}
