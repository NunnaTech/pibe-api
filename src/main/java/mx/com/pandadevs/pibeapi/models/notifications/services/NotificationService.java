package mx.com.pandadevs.pibeapi.models.notifications.services;

import mx.com.pandadevs.pibeapi.models.modes.Mode;
import mx.com.pandadevs.pibeapi.models.notifications.dto.NotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import mx.com.pandadevs.pibeapi.models.notifications.repository.NotificationRepository;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NotificationService implements ServiceInterface<Integer,NotificationDto> {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public List<NotificationDto> getAll() {
        return null;
    }

    @Override
    public Optional<NotificationDto> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public NotificationDto save(NotificationDto entity) {
        return null;
    }

    @Override
    public Optional<NotificationDto> update(NotificationDto entity) {
        return Optional.empty();
    }

    @Override
    public Optional<NotificationDto> partialUpdate(Integer id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    public void fillInitialData() {
        if (notificationRepository.count() > 0) return;
        ArrayList<Notification> notifications = new ArrayList<Notification>() {{
            add(new Notification("Finalizado","Finalización de un candidato", true));
            add(new Notification("Vacante cubierta","Candidato elegido", true));
            add(new Notification("Compartir","Vacante compatida", true));
            add(new Notification("Contacto","Persona añadida", true));
            add(new Notification("Estado","Estado de la vacante", true));
        }};
        notificationRepository.saveAll(notifications);
    }
}
