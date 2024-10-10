package backend.test.client_storage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Client storage API", version = "1.0.0",
		description = "API for managing clients and their contact information"))
public class ClientStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientStorageApplication.class, args);
	}

}
