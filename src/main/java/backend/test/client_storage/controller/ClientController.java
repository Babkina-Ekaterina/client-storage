package backend.test.client_storage.controller;

import backend.test.client_storage.data.dto.*;
import backend.test.client_storage.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @PostMapping("/new_client")
    public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientAddDto clientAddDto) {
        return ResponseEntity.ok(clientService.addClient(clientAddDto));
    }

    @PostMapping("/new_contact")
    public ResponseEntity<ContactInfoDto> addContactInfo(@RequestBody @Valid ContactInfoAddDto contactInfoAddDto) {
        return ResponseEntity.ok(clientService.addContactInfo(contactInfoAddDto));
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/contacts/{clientId}")
    public ResponseEntity<List<ContactInfoDto>> getContactsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getContactsByClientId(clientId));
    }

    @GetMapping("/contacts/{clientId}/{contactType}")
    public ResponseEntity<List<ContactInfoDto>> getContactsByClientIdAndType(
            @PathVariable Long clientId,
            @PathVariable String contactType) {
        return ResponseEntity.ok(clientService.getContactsByClientIdAndType(clientId, contactType));
    }
}
