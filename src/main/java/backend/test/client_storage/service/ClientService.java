package backend.test.client_storage.service;

import backend.test.client_storage.data.dto.ClientAddDto;
import backend.test.client_storage.data.dto.ClientDto;
import backend.test.client_storage.data.dto.ContactInfoAddDto;
import backend.test.client_storage.data.dto.ContactInfoDto;
import backend.test.client_storage.data.entity.Client;
import backend.test.client_storage.data.entity.ContactInfo;
import backend.test.client_storage.data.entity.ContactType;
import backend.test.client_storage.data.mapper.ClientMapper;
import backend.test.client_storage.data.mapper.ContactInfoMapper;
import backend.test.client_storage.data.repository.ClientRepository;
import backend.test.client_storage.data.repository.ContactInfoRepository;
import backend.test.client_storage.data.repository.ContactTypeRepository;
import backend.test.client_storage.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;
    private ContactTypeRepository contactTypeRepository;
    private ContactInfoRepository contactInfoRepository;
    private ClientMapper clientMapper;
    private ContactInfoMapper contactInfoMapper;

    public ClientDto addClient(ClientAddDto clientAddDto) {
        Client client = clientRepository.save(clientMapper.dtoToEntity(clientAddDto));
        return clientMapper.entityToDto(client);
    }

    public ContactInfoDto addContactInfo(ContactInfoAddDto contactInfoAddDto) {
        Long id = contactInfoAddDto.getClientId();
        String contactTypeName = contactInfoAddDto.getContactTypeName();

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + id + " not found"));
        ContactType contactType = contactTypeRepository.findByName(contactTypeName)
                .orElseThrow(() -> new ResourceNotFoundException("Contact type '" + contactTypeName + "' not found"));

        ContactInfo contactInfo = contactInfoRepository.save(contactInfoMapper.fieldsToEntity(
                client, contactType, contactInfoAddDto.getValue()));
        return contactInfoMapper.entityToDto(contactInfo);
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + id + " not found"));
        return clientMapper.entityToDto(client);
    }

    public List<ContactInfoDto> getContactsByClientId(Long clientId) {
        clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + clientId + " not found"));

        return contactInfoRepository.findByClientId(clientId).stream()
                .map(contactInfoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<ContactInfoDto> getContactsByClientIdAndType(Long clientId, String contactType) {
        clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + clientId + " not found"));
        contactTypeRepository.findByName(contactType)
                .orElseThrow(() -> new ResourceNotFoundException("Contact type '" + contactType + "' not found"));


        return contactInfoRepository.findByClientIdAndContactTypeName(clientId, contactType).stream()
                .map(contactInfoMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
