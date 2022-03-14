package uz.pdp.pdpspring6thlessonhomework2nd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.PaymentDto;
import uz.pdp.pdpspring6thlessonhomework2nd.service.PaymentService;

@RestController
@RequestMapping("/api/auth/payment")
public class PaymentController {
    @Autowired
    PaymentService service;

    @PostMapping
    public HttpEntity<?> addPayment(@RequestBody PaymentDto dto) {
        ApiResponse apiResponse = service.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{simCardNumber}")
    public ResponseEntity<?>getOneClientsPaymentHistory(String simCardNumber){
        return ResponseEntity.ok(service.getOneClientsPaymentHistory(simCardNumber));
    }

    @GetMapping("/byClient")
    public ResponseEntity<?>getPaymentHistoryByClient(){
        return ResponseEntity.ok(service.getPaymentHistoryByClient());
    }

    @GetMapping
    public ResponseEntity<?>getAllPaymentHistory(){
        return ResponseEntity.ok(service.getAll());
    }






}
