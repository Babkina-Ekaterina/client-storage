package backend.test.client_storage.controller;

import backend.test.client_storage.data.dto.*;
import backend.test.client_storage.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
@AllArgsConstructor
@Tag(name = "Clients", description = "Endpoints for managing clients")
public class ClientController {
    private ClientService clientService;

    @PostMapping("/new_client")
    @Operation(summary = "Adding new client")
    public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientAddDto clientAddDto) {
        return ResponseEntity.ok(clientService.addClient(clientAddDto));
    }

    @PostMapping("/new_contact")
    @Operation(summary = "Adding new contact")
    public ResponseEntity<ContactInfoDto> addContactInfo(@RequestBody @Valid ContactInfoAddDto contactInfoAddDto) {
        return ResponseEntity.ok(clientService.addContactInfo(contactInfoAddDto));
    }

    @GetMapping("/clients")
    @Operation(summary = "Getting all clients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/client/{id}")
    @Operation(summary = "Getting client by id")
    public ResponseEntity<ClientDto> getClientById(
            @Parameter(description = "Unique identifier of the client")
            @PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/contacts/{clientId}")
    @Operation(summary = "Getting al contacts by client id")
    public ResponseEntity<List<ContactInfoDto>> getContactsByClientId(
            @Parameter(description = "Unique identifier of the client")
            @PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getContactsByClientId(clientId));
    }

    @GetMapping("/contacts/{clientId}/{contactType}")
    @Operation(summary = "Getting al contacts by client id and contact type")
    public ResponseEntity<List<ContactInfoDto>> getContactsByClientIdAndType(
            @Parameter(description = "Unique identifier of the client")
            @PathVariable Long clientId,

            @Parameter(description = "Contact type (e.g. email, phoneNumber...)")
            @PathVariable String contactType) {
        return ResponseEntity.ok(clientService.getContactsByClientIdAndType(clientId, contactType));
    }
}
