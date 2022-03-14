package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Tariff;

import java.util.UUID;


public interface TariffRepository extends JpaRepository<Tariff, UUID> {
    boolean existsByName(String name);
    Tariff findByName(String name);
}
