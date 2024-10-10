package backend.test.client_storage.controller;

import backend.test.client_storage.data.dto.ContactTypeAddDto;
import backend.test.client_storage.data.dto.ContactTypeDto;
import backend.test.client_storage.service.ContactTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Contact types", description = "Endpoints for managing contact types")
public class ContactTypeController {
    private ContactTypeService contactTypeService;

    @PostMapping("/new_contact_type")
    @Operation(summary = "Adding new contact type")
    public ResponseEntity<ContactTypeDto> addContactType(@RequestBody @Valid ContactTypeAddDto contactTypeAddDto) {
        return ResponseEntity.ok(contactTypeService.addContactType(contactTypeAddDto));
    }
}
