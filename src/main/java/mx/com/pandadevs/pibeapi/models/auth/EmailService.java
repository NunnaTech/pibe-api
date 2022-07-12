package mx.com.pandadevs.pibeapi.models.auth;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import mx.com.pandadevs.pibeapi.models.auth.common.AuthRequest;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {
    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private UserRepository userRepository;
    @Value("${email-address}")
    private String EMAIL_ADDRESS;

    @Value("${email-key}")
    private String EMAIL_KEY;

    @Value("${template-email-new-vacant}")
    private String TEMPLATE_EMAIL_NEW_VACANT;

    @Value("${template-email-currently-process}")
    private String TEMPLATE_EMAIL_CURRENTLY_PROCESS;

    @Value("${template-email-share-vacant}")
    private String TEMPLATE_EMAIL_SHARE_VACANT;

    @Value("${template-email-password-recovery}")
    private String TEMPLATE_EMAIL_PASSWORD_RECOVERY;

    /*
    * TEST DATA: (addDynamicTemplateData)
    *   "urlImage": La URL de la imagen
    *   "vacant": Título de la vacante o detalles
    * */
    public boolean sendEmailNewVacant(User user)  {
        boolean flag = false;
        try {
            SendGrid sg = new SendGrid(EMAIL_KEY);
            Mail mail = getMail(user.getEmail());
            mail.setTemplateId(TEMPLATE_EMAIL_NEW_VACANT);
            mail.personalization.get(0).addDynamicTemplateData("urlImage", "https://picsum.photos/200/300");
            mail.personalization.get(0).addDynamicTemplateData("vacant", "Diseñador de algo");
            Request request = getRequest(mail);
            Response response = sg.api(request);
            flag = response.getStatusCode() == 202;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    /*
     * TEST DATA: (addDynamicTemplateData)
     *  "state": Estado actual
     *  "reject": Si fue rechazado colocar es
     *  "vacant": Título de la vacante o detalles
     *  "title: Breve introducción
     *  "description": Mensaje de despedida del correo
     * */
    public boolean sendEmailCurrentlyProccess(User user){
        boolean flag = false;
        try {
            SendGrid sg = new SendGrid(EMAIL_KEY);
            Mail mail = getMail(user.getEmail());
            mail.setTemplateId(TEMPLATE_EMAIL_CURRENTLY_PROCESS);
            mail.personalization.get(0).addDynamicTemplateData("state", "");
            mail.personalization.get(0).addDynamicTemplateData("reject", "Proceso finalizado");
            mail.personalization.get(0).addDynamicTemplateData("vacant", "Diseñador de interfaces UX/UI");
            mail.personalization.get(0).addDynamicTemplateData("title", "Por este medio se le hace la notificación que a sido: ");
            mail.personalization.get(0).addDynamicTemplateData("description", "No es el final, más vacantes te esperan");
            Request request = getRequest(mail);
            Response response = sg.api(request);
            flag = response.getStatusCode() == 202;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    /*
     * TEST DATA: (addDynamicTemplateData)
     *  "urlImage": URL de la imagen de la vacante
     *  "urlBtn": URL a donde redireccionará el botón
     *  "fullName": Nombre parcial o completo de la persona
     *  "vacant: Título de la vacante o detalles
     * */
    public boolean sendEmailShareVacant(User user){
        boolean flag = false;
        try {
            SendGrid sg = new SendGrid(EMAIL_KEY);
            Mail mail = getMail(user.getEmail());
            mail.setTemplateId(TEMPLATE_EMAIL_SHARE_VACANT);
            mail.personalization.get(0).addDynamicTemplateData("urlImage", "");
            mail.personalization.get(0).addDynamicTemplateData("urlBtn", "");
            mail.personalization.get(0).addDynamicTemplateData("fullName", "");
            mail.personalization.get(0).addDynamicTemplateData("vacant", "");
            Request request = getRequest(mail);
            Response response = sg.api(request);
            flag = response.getStatusCode() == 202;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    /*
     * TEST DATA: (addDynamicTemplateData)
     *  "url": a donde redireccionará el botón
     * */
    public boolean sendEmailPasswordRecovery(AuthRequest request){
        String token = UUID.randomUUID().toString();
        boolean flag = false;
        Optional<User> user = userRepository.findByEmailAndActiveTrue(request.getEmail());
        if (user== null|| !user.get().getActive()) return  false;
        try {
            user.get().setLinkRestorePassword(token);
            SendGrid sg = new SendGrid(EMAIL_KEY);
            Mail mail = getMail(user.get().getEmail());
            mail.setTemplateId(TEMPLATE_EMAIL_PASSWORD_RECOVERY);
            mail.personalization.get(0).addDynamicTemplateData("codigo", token);
            Request mailRequest = getRequest(mail);
            Response response = sg.api(mailRequest);
            System.out.println(response.getStatusCode());
            flag = response.getStatusCode() == 202;
            userRepository.save(user.get());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return flag;
    }

    private Mail getMail(String toEmail){
        return new Mail(new Email(EMAIL_ADDRESS), "SUBJECT", new Email(toEmail), new Content("text/html", "CONTENT"));
    }

    private Request getRequest(Mail mail) throws IOException {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        return request;
    }
}
