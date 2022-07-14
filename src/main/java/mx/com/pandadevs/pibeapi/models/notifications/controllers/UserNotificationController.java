package mx.com.pandadevs.pibeapi.models.notifications.controllers;

import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.services.UserNotificationService;
import mx.com.pandadevs.pibeapi.utils.interfaces.ControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("notification/")
public class UserNotificationController implements ControllerInterface<UserNotificationDto, Long> {
    @Autowired
    private UserNotificationService service;

    @Override
    public ResponseEntity<List<UserNotificationDto>> getAll() {
        return null;
    }
    @RequestMapping("/{username}")
    public ResponseEntity<List<UserNotificationDto>> getAllByUser(@PathVariable("username") String username) {
        return new ResponseEntity(service.getAllByUser(username), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserNotificationDto> getOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserNotificationDto> save(UserNotificationDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<UserNotificationDto> update(UserNotificationDto entity) {
        return null;
    }

    @Override
    public ResponseEntity<UserNotificationDto> partialUpdate(Long id, Map<Object, Object> fields) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return null;
    }
}
