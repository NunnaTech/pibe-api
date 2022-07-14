package mx.com.pandadevs.pibeapi.models.notifications.services;

import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotification;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotificationPK;
import mx.com.pandadevs.pibeapi.models.notifications.mapper.UserNotificationMapper;
import mx.com.pandadevs.pibeapi.models.notifications.repository.UserNotificationRepository;
import mx.com.pandadevs.pibeapi.models.users.UserService;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class UserNotificationService implements ServiceInterface<UserNotificationPK,UserNotificationDto> {

    private final UserNotificationMapper  mapper;

    public UserNotificationService(UserNotificationMapper mapper){
        this.mapper = mapper;
    }

    @Autowired
    private UserNotificationRepository repository;

    @Override
    public List<UserNotificationDto> getAll() {
        return null;
    }
    public List<UserNotificationDto> getAllByUser(String username) {
        return mapper.toNotificationsDto(repository.findByUserUsernameAndUserActiveTrue(username));
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
