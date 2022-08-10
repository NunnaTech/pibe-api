package mx.com.pandadevs.pibeapi.models.vacants.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import mx.com.pandadevs.pibeapi.models.auth.EmailService;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotification;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotificationPK;
import mx.com.pandadevs.pibeapi.models.notifications.repository.UserNotificationRepository;
import mx.com.pandadevs.pibeapi.models.processes.Process;
import mx.com.pandadevs.pibeapi.models.processes.ProcessRepository;
import mx.com.pandadevs.pibeapi.models.processes.dto.ProcessDto;
import mx.com.pandadevs.pibeapi.models.users.User;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.vacants.dto.UserVacantDto;
import mx.com.pandadevs.pibeapi.models.vacants.dto.VacantProcessDto;
import mx.com.pandadevs.pibeapi.models.vacants.entities.UserVacant;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.mapper.UserVacantMapper;
import mx.com.pandadevs.pibeapi.models.vacants.repository.UserVacantRepository;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import mx.com.pandadevs.pibeapi.security.LogJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserVacantService {

    private Logger logger = LoggerFactory.getLogger(UserVacantService.class);

    private final UserVacantMapper mapper;

    public UserVacantService(UserVacantMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserVacantRepository userVacantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VacantRepository vacantRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private UserNotificationRepository userNotificationRepository;
    @Autowired
    private LogJwtService logJwtService;

    private Optional<UserVacant> findByVacantAndUser(Long idUser, Integer idVacant) {
        return userVacantRepository.findByUser_IdAndVacant_Id(idUser, idVacant);
    }

    @Transactional(readOnly = true)
    public List<UserVacantDto> getVacantsByUser(String username, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_CANDIDATE") && auth.get("username").equals(username)) {
            return mapper.toUserVacantsDto(userVacantRepository.findAllByUser_Username(username));
        }
        return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public List<VacantProcessDto> getUsersByVacant(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_RECRUITER")) {
            return mapper.toVacantsProcessDto(userVacantRepository.findAllByVacant_Id(id));
        }
        return new ArrayList<>();
    }

    @Transactional
    public Boolean applyToVacant(Integer id, String username, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_CANDIDATE") && auth.get("username").equals(username)) {
            User user = userRepository.findByUsername(username);
            Optional<Vacant> vacant = vacantRepository.findByIdAndActiveIsTrue(id);
            Optional<Process> process = processRepository.findByIdAndActiveIsTrue(1);
            Optional<UserVacant> userVacant = findByVacantAndUser(user.getId(), id);
            if (vacant.isPresent() && process.isPresent() && userVacant.isEmpty()) {
                emailService.sendEmailNewVacant(user, vacant.get());
                userVacantRepository.save(new UserVacant(user, vacant.get(), process.get()));
                UserNotificationPK fpk = new UserNotificationPK(5, user.getId());
                UserNotification ntf = new UserNotification(fpk, "Postulado", true);
                userNotificationRepository.save(ntf);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Boolean processToVacant(Integer id, ProcessDto processDto, String bearerToken) throws JsonProcessingException {
        String message = "";
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_RECRUITER")) {
            Optional<UserVacant> userVacant = userVacantRepository.findById(id);
            if (userVacant.isPresent()) {
                userVacant.get().setProcess(processRepository.findByIdAndActiveIsTrue(processDto.getId()).get());
                emailService.sendEmailCurrentlyProccess(userVacant.get(), userVacant.get().getProcess().getName().equals("Finalizado"));
                UserNotificationPK fpk = new UserNotificationPK(5, userVacant.get().getUser().getId());
                switch (id) {
                    case 1:
                        message = "Postulado";
                        break;
                    case 2:
                        message = "CV Visto";
                        break;
                    case 3:
                        message = "Entrevista";
                        break;
                    case 4:
                        message = "Idóneo";
                        break;
                    case 5:
                        message = "Contratado";
                        break;
                }
                UserNotification ntf = new UserNotification(fpk, message, true);
                userNotificationRepository.save(ntf);
                userVacantRepository.save(userVacant.get());
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Boolean deleteUserToVacant(Integer id, String bearerToken) throws JsonProcessingException {
        Map<String, String> auth = logJwtService.getUsernameAndRole(bearerToken);
        if (auth.get("role").contains("ROLE_RECRUITER")) {
            Optional<UserVacant> userVacant = userVacantRepository.findById(id);
            if (userVacant.isPresent()) {
                userVacantRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
