package mx.com.pandadevs.pibeapi.models.notifications.repository;

import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer> {
    List<UserNotification> findByUserUsernameAndUserActiveTrue(String username);
}
