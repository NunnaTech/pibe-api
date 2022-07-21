package mx.com.pandadevs.pibeapi.models.notifications.controllers;

import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserNotificationController{
    @Autowired
    private UserNotificationService service;

    @RequestMapping("/{username}/notifications")
    public ResponseEntity<List<UserNotificationDto>> getAllByUser(@PathVariable("username") String username) {
        return new ResponseEntity(service.getAllByUser(username), HttpStatus.OK);
    }
}
