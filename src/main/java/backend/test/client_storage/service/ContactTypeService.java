package backend.test.client_storage.service;

import backend.test.client_storage.data.dto.ContactTypeAddDto;
import backend.test.client_storage.data.dto.ContactTypeDto;
import backend.test.client_storage.data.entity.ContactType;
import backend.test.client_storage.data.mapper.ContactTypeMapper;
import backend.test.client_storage.data.repository.ContactTypeRepository;
import backend.test.client_storage.exception.ResourceAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactTypeService {
    private ContactTypeRepository contactTypeRepository;
    private ContactTypeMapper contactTypeMapper;

    public ContactTypeDto addContactType(ContactTypeAddDto contactTypeAddDto) {
        if (contactTypeRepository.existsByName(contactTypeAddDto.getName())) {
            throw new ResourceAlreadyExistsException("Contact type with name '"
                    + contactTypeAddDto.getName() + "' already exists");
        }

        ContactType contactType = contactTypeRepository.save(contactTypeMapper.dtoToEntity(contactTypeAddDto));
        return contactTypeMapper.entityToDto(contactType);
    }
}
