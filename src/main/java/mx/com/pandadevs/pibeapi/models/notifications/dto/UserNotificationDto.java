package mx.com.pandadevs.pibeapi.models.notifications.dto;

public class UserNotificationDto {

    private NotificationDto notification;

    private String content;

    private Boolean seen;

    public NotificationDto getNotification() {
        return notification;
    }

    public void setNotification(NotificationDto notification) {
        this.notification = notification;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}
