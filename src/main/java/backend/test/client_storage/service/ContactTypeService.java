package backend.test.client_storage.service;

import backend.test.client_storage.data.dto.ContactTypeAddDto;
import backend.test.client_storage.data.dto.ContactTypeDto;
import backend.test.client_storage.data.entity.ContactType;
import backend.test.client_storage.data.repository.ContactTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactTypeService {
    private ContactTypeRepository contactTypeRepository;

    public ContactTypeDto addContactType(ContactTypeAddDto contactTypeAddDto) {
        ContactType contactType = new ContactType();
        contactType.setName(contactTypeAddDto.getName());

        ContactType ct = contactTypeRepository.save(contactType);
        return new ContactTypeDto(ct.getId(), ct.getName());
    }
}
