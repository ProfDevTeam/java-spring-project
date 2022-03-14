package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.ServiceCategory;


public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
boolean existsByName(String name);
}
