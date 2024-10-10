package backend.test.client_storage.data.mapper;

import backend.test.client_storage.data.dto.ContactInfoDto;
import backend.test.client_storage.data.entity.Client;
import backend.test.client_storage.data.entity.ContactInfo;
import backend.test.client_storage.data.entity.ContactType;
import org.springframework.stereotype.Component;

@Component
public class ContactInfoMapper {
    public ContactInfo fieldsToEntity(Client client, ContactType contactType, String value) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setClient(client);
        contactInfo.setContactType(contactType);
        contactInfo.setValue(value);
        return contactInfo;
    }

    public ContactInfoDto entityToDto(ContactInfo contactInfo) {
        Long id = contactInfo.getId();
        String contactTypeName = contactInfo.getContactType().getName();
        String value = contactInfo.getValue();
        return new ContactInfoDto(id, contactTypeName, value);
    }
}
