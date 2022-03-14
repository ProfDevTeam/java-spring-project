package uz.pdp.pdpspring6thlessonhomework2nd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Payment;


import java.util.List;
import java.util.UUID;


public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findAllBySimCardNumber(String simCard_number);

}
