package pl.warsaw.hackaton.nextgensalesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.warsaw.hackaton.nextgensalesservice.entity.CustomerEntity;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> getCustomerByPesel(String pesel);
}
