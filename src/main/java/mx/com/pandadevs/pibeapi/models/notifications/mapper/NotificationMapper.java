package mx.com.pandadevs.pibeapi.models.notifications.mapper;
// Java

import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import mx.com.pandadevs.pibeapi.models.notifications.dto.NotificationDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper MAPPER = Mappers.getMapper( NotificationMapper.class);
    
    @Mappings({
        @Mapping(source = "name", target = "type"),
        @Mapping(source = "description", target = "description"),
    })
    NotificationDto toNotificationDto(Notification notification);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "notifications", ignore = true)
    })
    Notification toNotification(NotificationDto notificationDto);

    List<NotificationDto> toNotificationsDto(List<NotificationDto> notificationDtos);
}
