package mx.com.pandadevs.pibeapi.models.contacts.mapper;

import mx.com.pandadevs.pibeapi.models.contacts.dto.ContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.dto.SingleContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import mx.com.pandadevs.pibeapi.models.users.mapper.UserMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserMapper.class})

public interface ContactMapper {
    ContactMapper MAPPER = Mappers.getMapper( ContactMapper.class);

    @Mappings({
            @Mapping(source = "contact", target = "contact"),
            @Mapping(source = "user", target = "user")
    })
    ContactDto toContactDto (Contact contact);
    List<ContactDto> toContactsDto(List<Contact> contacts);

    @Mappings({
            @Mapping(source = "contact", target = "contact")
    })
    SingleContactDto toSingleContactDto(Contact contact);
    List<SingleContactDto> toSingleContactsDto(List<Contact> contacts);

    @InheritInverseConfiguration
    Contact toContact(ContactDto contactDto);

    @InheritInverseConfiguration
    Contact toSingleDto(SingleContactDto singleContactDto);
}
