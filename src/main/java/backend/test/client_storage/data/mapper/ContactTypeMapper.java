package backend.test.client_storage.data.mapper;

import backend.test.client_storage.data.dto.ClientAddDto;
import backend.test.client_storage.data.dto.ClientDto;
import backend.test.client_storage.data.dto.ContactTypeAddDto;
import backend.test.client_storage.data.dto.ContactTypeDto;
import backend.test.client_storage.data.entity.Client;
import backend.test.client_storage.data.entity.ContactType;
import org.springframework.stereotype.Component;

@Component
public class ContactTypeMapper {
    public ContactType dtoToEntity(ContactTypeAddDto contactTypeAddDto) {
        ContactType contactType = new ContactType();
        contactType.setName(contactTypeAddDto.getName());
        return contactType;
    }

    public ContactTypeDto entityToDto(ContactType contactType) {
        Long id = contactType.getId();
        String name = contactType.getName();
        return new ContactTypeDto(id, name);
    }
}
