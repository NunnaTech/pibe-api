package mx.com.pandadevs.pibeapi.models.notifications.controllers;

import io.swagger.annotations.Api;
import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.services.UserNotificationService;
import mx.com.pandadevs.pibeapi.models.vacants.controller.VacantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Api(tags = "User Notifications")
public class UserNotificationController {
    @Autowired
    private UserNotificationService service;

    private Logger logger = LoggerFactory.getLogger(UserNotificationController.class);


    @RequestMapping("/{username}/notifications")
    public ResponseEntity<List<UserNotificationDto>> getAllByUser(@PathVariable("username") String username) {
        return new ResponseEntity(service.getAllByUser(username), HttpStatus.OK);
    }

    @PostMapping(value = "/notification/share/vacant")
    public ResponseEntity<Boolean> shareVacantNotification(@RequestBody Map<Object, Object> fields
    ) {
        try {
            if (service.shareVacant(fields)) return new ResponseEntity<>(true, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.toString());
            logger.error(e.getLocalizedMessage());
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
