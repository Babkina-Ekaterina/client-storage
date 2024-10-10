package backend.test.client_storage;

import backend.test.client_storage.controller.ClientController;
import backend.test.client_storage.data.dto.ClientAddDto;
import backend.test.client_storage.data.dto.ClientDto;
import backend.test.client_storage.data.dto.ContactInfoAddDto;
import backend.test.client_storage.data.dto.ContactInfoDto;
import backend.test.client_storage.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientControllerTests {
    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addClient_ShouldReturnClientDto() {
        // Given
        ClientAddDto clientAddDto = new ClientAddDto();
        ClientDto clientDto = new ClientDto(1L, "test");
        when(clientService.addClient(any(ClientAddDto.class))).thenReturn(clientDto);

        // When
        ResponseEntity<ClientDto> response = clientController.addClient(clientAddDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("test", response.getBody().getName());
        verify(clientService, times(1)).addClient(any(ClientAddDto.class));
    }

    @Test
    void addContact_ShouldReturnContactInfoDto() {
        // Given
        ContactInfoAddDto contactInfoAddDto = new ContactInfoAddDto();
        ContactInfoDto contactInfoDto = new ContactInfoDto(1L, "email", "test@gmail.com");
        when(clientService.addContactInfo(any(ContactInfoAddDto.class))).thenReturn(contactInfoDto);

        // When
        ResponseEntity<ContactInfoDto> response = clientController.addContactInfo(contactInfoAddDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("email", response.getBody().getContactTypeName());
        assertEquals("test@gmail.com", response.getBody().getValue());
        verify(clientService, times(1)).addContactInfo(any(ContactInfoAddDto.class));
    }

    @Test
    void getAllClients_ShouldReturnListOfClientDto() {
        // Given
        ClientDto clientDto1 = new ClientDto();
        ClientDto clientDto2 = new ClientDto();
        List<ClientDto> clients = Arrays.asList(clientDto1, clientDto2);
        when(clientService.getAllClients()).thenReturn(clients);

        // When
        ResponseEntity<List<ClientDto>> response = clientController.getAllClients();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void getClientById_ShouldReturnClientDto() {
        // Given
        ClientDto clientDto = new ClientDto(1L, "test");
        when(clientService.getClientById(1L)).thenReturn(clientDto);

        // When
        ResponseEntity<ClientDto> response = clientController.getClientById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("test", response.getBody().getName());
        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    void getContactsByClientId_ShouldReturnListOfContactInfoDto() {
        // Given
        ContactInfoDto contactInfoDto1 = new ContactInfoDto();
        ContactInfoDto contactInfoDto2 = new ContactInfoDto();
        List<ContactInfoDto> contactInfos = Arrays.asList(contactInfoDto1, contactInfoDto2);
        when(clientService.getContactsByClientId(1L)).thenReturn(contactInfos);

        // When
        ResponseEntity<List<ContactInfoDto>> response = clientController.getContactsByClientId(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactInfos, response.getBody());
        verify(clientService, times(1)).getContactsByClientId(1L);
    }

    @Test
    void getContactsByClientIdAndType_ShouldReturnListOfContactInfoDto() {
        // Given
        ContactInfoDto contactInfoDto1 = new ContactInfoDto();
        ContactInfoDto contactInfoDto2 = new ContactInfoDto();
        List<ContactInfoDto> contactInfos = Arrays.asList(contactInfoDto1, contactInfoDto2);
        when(clientService.getContactsByClientIdAndType(1L, "email")).thenReturn(contactInfos);

        // When
        ResponseEntity<List<ContactInfoDto>> response = clientController.getContactsByClientIdAndType(1L, "email");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactInfos, response.getBody());
        verify(clientService, times(1)).getContactsByClientIdAndType(1L, "email");
    }
}
