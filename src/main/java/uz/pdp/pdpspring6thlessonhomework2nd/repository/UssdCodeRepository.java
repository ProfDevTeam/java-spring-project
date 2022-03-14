package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.UssdCode;


public interface UssdCodeRepository extends JpaRepository<UssdCode, Integer> {

   boolean existsByCode(String code);

}
