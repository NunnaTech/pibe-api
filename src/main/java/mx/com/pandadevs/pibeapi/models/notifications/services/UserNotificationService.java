package mx.com.pandadevs.pibeapi.models.notifications.services;

import mx.com.pandadevs.pibeapi.models.auth.EmailService;
import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotification;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotificationPK;
import mx.com.pandadevs.pibeapi.models.notifications.mapper.UserNotificationMapper;
import mx.com.pandadevs.pibeapi.models.notifications.repository.NotificationRepository;
import mx.com.pandadevs.pibeapi.models.notifications.repository.UserNotificationRepository;
import mx.com.pandadevs.pibeapi.models.profile.Profile;
import mx.com.pandadevs.pibeapi.models.profile.ProfileRepository;
import mx.com.pandadevs.pibeapi.models.users.UserRepository;
import mx.com.pandadevs.pibeapi.models.vacants.entities.Vacant;
import mx.com.pandadevs.pibeapi.models.vacants.repository.VacantRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserNotificationService implements ServiceInterface<UserNotificationPK, UserNotificationDto> {

    private Logger logger = LoggerFactory.getLogger(UserNotificationService.class);

    private final UserNotificationMapper mapper;


    public UserNotificationService(UserNotificationMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    private UserNotificationRepository repository;


    @Autowired
    private VacantRepository vacantRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<UserNotificationDto> getAll() {
        return null;
    }

    public List<UserNotificationDto> getAllByUser(String username) {
        return mapper.toNotificationsDto(repository.findByUserUsernameAndUserActiveTrue(username));
    }

    public Boolean shareVacant(Map<Object, Object> fields) {
        try {
            Optional<Profile> toUsernameProfile = profileRepository.findByUserUsernameAndUserActiveTrue(fields.get("toUsername").toString());
            Optional<Profile> forUsernameProfile = profileRepository.findByUserUsernameAndUserActiveTrue(fields.get("forUsername").toString());
            Optional<Notification> notification = notificationRepository.findById(3);
            Optional<Vacant> vacant = vacantRepository.findById(Integer.parseInt(fields.get("idVacant").toString()));
            if (toUsernameProfile.isPresent() && forUsernameProfile.isPresent() && notification.isPresent() && vacant.isPresent()) {
                UserNotificationPK fpk = new UserNotificationPK(3, forUsernameProfile.get().getUser().getId());
                UserNotification ntf = new UserNotification(fpk, fields.get("url").toString(), false);
                repository.save(ntf);
                emailService.sendEmailShareVacant(toUsernameProfile.get(), forUsernameProfile.get(), fields.get("url").toString(), vacant.get());
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.toString());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause().getMessage());
        }
        return false;
    }

    @Override
    public Optional<UserNotificationDto> getById(UserNotificationPK id) {
        return Optional.empty();
    }

    @Override
    public UserNotificationDto save(UserNotificationDto entity) {
        return null;
    }

    @Override
    public Optional<UserNotificationDto> update(UserNotificationDto entity) {
        return Optional.empty();
    }

    @Override
    public Optional<UserNotificationDto> partialUpdate(UserNotificationPK id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(UserNotificationPK id) {
        return null;
    }
}
