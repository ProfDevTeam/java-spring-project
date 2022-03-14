package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Client;


import java.util.Optional;
import java.util.UUID;


public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByPassportNumber(String passportNumber);

    boolean existsByPassportNumber(String passportNumber);


}
