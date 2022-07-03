package mx.com.pandadevs.pibeapi.models.notifications.services;

import mx.com.pandadevs.pibeapi.models.notifications.dto.NotificationDto;
import mx.com.pandadevs.pibeapi.utils.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NotificationService implements ServiceInterface<NotificationDto> {
    @Override
    public List<NotificationDto> getAll() {
        return null;
    }

    @Override
    public Optional<NotificationDto> getById(Long id) {
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
    public Optional<NotificationDto> partialUpdate(Long id, Map<Object, Object> fields) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
