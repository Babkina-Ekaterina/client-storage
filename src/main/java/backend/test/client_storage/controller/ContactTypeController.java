package backend.test.client_storage.controller;

import backend.test.client_storage.data.dto.ContactTypeAddDto;
import backend.test.client_storage.data.dto.ContactTypeDto;
import backend.test.client_storage.service.ContactTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/storage")
@AllArgsConstructor
public class ContactTypeController {
    private ContactTypeService contactTypeService;

    @PostMapping("/new_contact_type")
    public ResponseEntity<ContactTypeDto> addContactType(@RequestBody @Valid ContactTypeAddDto contactTypeAddDto) {
        return ResponseEntity.ok(contactTypeService.addContactType(contactTypeAddDto));
    }
}
