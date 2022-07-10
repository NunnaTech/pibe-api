package mx.com.pandadevs.pibeapi.models.contacts.dto;

import mx.com.pandadevs.pibeapi.models.users.dto.UserDto;

public class ContactDto {
    private UserDto contact;
    // Getters && Setters

    public UserDto getContact() {
        return contact;
    }

    public void setContact(UserDto contact) {
        this.contact = contact;
    }
}
