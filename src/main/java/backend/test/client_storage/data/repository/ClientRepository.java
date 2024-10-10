package backend.test.client_storage.data.repository;

import backend.test.client_storage.data.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
