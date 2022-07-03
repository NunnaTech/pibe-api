package mx.com.pandadevs.pibeapi.models.notifications.repository;

import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {}
