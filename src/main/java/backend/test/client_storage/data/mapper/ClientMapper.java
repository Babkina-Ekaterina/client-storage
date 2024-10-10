package backend.test.client_storage.data.mapper;

import backend.test.client_storage.data.dto.ClientAddDto;
import backend.test.client_storage.data.dto.ClientDto;
import backend.test.client_storage.data.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client dtoToEntity(ClientAddDto clientAddDto) {
        Client client = new Client();
        client.setName(clientAddDto.getName());
        return client;
    }

    public ClientDto entityToDto(Client client) {
        Long id = client.getId();
        String name = client.getName();
        return new ClientDto(id, name);
    }
}
