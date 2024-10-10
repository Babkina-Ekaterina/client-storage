package backend.test.client_storage.data.repository;

import backend.test.client_storage.data.entity.ContactInfo;
import backend.test.client_storage.data.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    List<ContactInfo> findByClientId(Long clientId);
    List<ContactInfo> findByClientIdAndContactTypeName(Long clientId, String contactTypeName);
}
