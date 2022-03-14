package uz.pdp.pdpspring6thlessonhomework2nd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Payment;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.SimCard;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.enums.PayType;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.PaymentDto;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.PaymentRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.RoleRepository;
import uz.pdp.pdpspring6thlessonhomework2nd.repository.SimCardRepository;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    SimCardRepository simCardRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse add(PaymentDto dto){
        Payment payment = new Payment();
        payment.setPayerId(dto.getPayerId());
        payment.setPayerName(dto.getPayerName());
        payment.setAmount(dto.getAmount());
        SimCard simCard = simCardRepository.findBySimCardNumber(dto.getSimCardNumber()).get();
        simCard.setBalance(simCard.getBalance()+ dto.getAmount());
        payment.setSimCard(simCard);
        if (dto.getPayType().equalsIgnoreCase("naqd")) payment.setPayType(PayType.NAQD);
        if (dto.getPayType().equalsIgnoreCase("humo")) payment.setPayType(PayType.HUMO);
        if (dto.getPayType().equalsIgnoreCase("click")) payment.setPayType(PayType.CLICK);
        if (dto.getPayType().equalsIgnoreCase("payme")) payment.setPayType(PayType.PAYME);
        paymentRepository.save(payment);
        return new ApiResponse("Payment amalga oshirildi", true);

    }
    public ApiResponse getAll(){
        List<Payment> paymentList = paymentRepository.findAll();
        return new ApiResponse("Barcha tolovlar tarihi", true, paymentList);
    }

    public ApiResponse getOneClientsPaymentHistory(String simCardNumber){
        List<Payment> allBySimCardNumber = paymentRepository.findAllBySimCardNumber(simCardNumber);
        SimCard simCard = simCardRepository.findBySimCardNumber(simCardNumber).get();
        return new ApiResponse(simCard.getCode()+simCard.getNumber()+
                " raqamli abonentning tolovlar tarihi", true, allBySimCardNumber);
    }

    public ApiResponse getPaymentHistoryByClient(){
      SimCard simCard = (SimCard) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Payment> allBySimCardNumber = paymentRepository.findAllBySimCardNumber(simCard.getSimCardNumber());
        return new ApiResponse(simCard.getCode()+simCard.getNumber()+" " +
                "raqimingizning tolovlar tarihi", true, allBySimCardNumber);
    }



}
