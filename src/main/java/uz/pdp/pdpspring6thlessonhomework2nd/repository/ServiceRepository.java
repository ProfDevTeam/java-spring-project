package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.EntertainingService;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ServiceRepository extends JpaRepository<EntertainingService, UUID> {
    Optional<EntertainingService> findByName(String name);
    boolean existsByName(String name);
    List<EntertainingService> findAllByOrderByCountAsc();

}
