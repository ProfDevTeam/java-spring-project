package uz.pdp.pdpspring6thlessonhomework2nd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.SimCard;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.service.SimCardService;


import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/SimCard")
public class SimCardController {

    @Autowired
    SimCardService simCardService;



    @PreAuthorize(value = "hasRole(ROLE_MANAGER)")
    @PostMapping("/addSimCard")
    public ResponseEntity<?> addSimCard(@RequestBody SimCardDto simCardDto) {
        ApiResponse apiResponse = simCardService.addSimCard(simCardDto);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyRole(ROLE_STAFF,ROLE_MANAGER)")
    @GetMapping("/unableSimCard")
    public ResponseEntity<?> unableSimCard(@RequestParam String code, @RequestParam String number) {
        ApiResponse apiResponse = simCardService.unableSimCard(code, number);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }


    @PreAuthorize(value = "hasRole(ROLE_CLIENT)")
    @GetMapping("/ussdCode")
    public ResponseEntity<?> unableSimCard(@RequestParam String ussdCode) {
        ApiResponse apiResponse = simCardService.getUssdCode(ussdCode);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }


    @PreAuthorize(value = "hasRole(ROLE_CLIENT)")
    @GetMapping("/changeTariff/{tariffId}")
    public ResponseEntity<?> unableSimCard(@PathVariable UUID tariffId) {
        ApiResponse apiResponse = simCardService.connectTariff(tariffId);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole(ROLE_CLIENT,ROLE_MANAGER,ROLE_STAFF)")
    @GetMapping("/getSimCards")
    public ResponseEntity<?> getAvailableSimCards() {
        Set<SimCard> simCards = simCardService.getSimCards();
        if (simCards.isEmpty()) return ResponseEntity.status(409).body(simCards);
        return ResponseEntity.ok(simCards);
    }

}
