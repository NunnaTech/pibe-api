package mx.com.pandadevs.pibeapi.models.notifications.mapper;
// Java

import mx.com.pandadevs.pibeapi.models.notifications.entities.Notification;
import mx.com.pandadevs.pibeapi.models.notifications.entities.UserNotification;
import mx.com.pandadevs.pibeapi.models.notifications.dto.NotificationDto;
import mx.com.pandadevs.pibeapi.models.notifications.dto.UserNotificationDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", uses = {NotificationMapper.class})
public interface UserNotificationMapper {

    UserNotificationMapper MAPPER = Mappers.getMapper( UserNotificationMapper.class);
    
    @Mappings({
        @Mapping(source = "notification", target = "notification"),
        @Mapping(source = "action", target = "content"),
        @Mapping(source = "seen", target = "seen"),
    })
    UserNotificationDto toUserNotificationDto(UserNotification userNotification);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "notifications", ignore = true)
    })
    Notification toNotification(NotificationDto notificationDto);

    List<UserNotificationDto> toNotificationsDto(List<UserNotification> notifications);
}
