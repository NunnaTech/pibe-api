package mx.com.pandadevs.pibeapi.models.contacts.mapper;

import mx.com.pandadevs.pibeapi.models.contacts.dto.ContactDto;
import mx.com.pandadevs.pibeapi.models.contacts.entity.Contact;
import mx.com.pandadevs.pibeapi.models.languages.dto.LanguageDto;
import mx.com.pandadevs.pibeapi.models.languages.entity.Language;
import mx.com.pandadevs.pibeapi.models.languages.mapper.LanguageMapper;
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
            @Mapping(source = "user", target = "contact"),
    })
    ContactDto toContactDto (Contact contact);

    List<ContactDto> toContactsDto(List<Contact> contacts);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    Contact toContact(ContactDto contactDto);
}
