package backend.test.client_storage;

import backend.test.client_storage.controller.ContactTypeController;
import backend.test.client_storage.data.dto.ContactTypeAddDto;
import backend.test.client_storage.data.dto.ContactTypeDto;
import backend.test.client_storage.service.ContactTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContactTypeControllerTests {
    @Mock
    private ContactTypeService contactTypeService;

    @InjectMocks
    private ContactTypeController contactTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addContactType_ShouldReturnContactTypeDto() {
        // Given
        ContactTypeAddDto contactTypeAddDto = new ContactTypeAddDto();
        ContactTypeDto contactTypeDto = new ContactTypeDto(1L, "email");
        when(contactTypeService.addContactType(any(ContactTypeAddDto.class))).thenReturn(contactTypeDto);

        // When
        ResponseEntity<ContactTypeDto> response = contactTypeController.addContactType(contactTypeAddDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("email", response.getBody().getName());
        verify(contactTypeService, times(1)).addContactType(any(ContactTypeAddDto.class));
    }
}
