package mx.com.pandadevs.pibeapi.models.contacts.dto;

import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;

public class ContactDto {

    private UserDto user;
    private UserDto contact;

    public UserDto getContact() {
        return contact;
    }

    public void setContact(UserDto contact) {
        this.contact = contact;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
