package uz.pdp.pdpspring6thlessonhomework2nd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Tariff;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.TariffDto;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.TariffDtoForClient;
import uz.pdp.pdpspring6thlessonhomework2nd.service.TariffService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tariff")
public class TariffController {
    @Autowired
    TariffService tariffService;


    @PreAuthorize(value = "hasRole('ROLE_MANAGER')")
    @PostMapping("/addTariff")
    public HttpEntity<?> addTariff(@RequestBody TariffDto tariffDto){
        ApiResponse apiResponse=tariffService.addTariff(tariffDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasRole('ROLE_MANAGER')")
    @PutMapping("/editTariff/{id}")
    public HttpEntity<?> editTariff(@PathVariable UUID id, @RequestBody TariffDto tariffDto){
        ApiResponse apiResponse=tariffService.editTariff(id,tariffDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasRole('ROLE_MANAGER')")
    @DeleteMapping("/deleteTariff/{id}")
    public HttpEntity<?> editTariff(@PathVariable UUID id){
        ApiResponse apiResponse=tariffService.deleteTariff(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_MANAGER','ROLE_DIRECTOR','ROLE_STAFF')")
    @GetMapping("/getAllTariffsForStaff")
    public HttpEntity<?> getTariffsForStaff(){
        List<Tariff> tariffList=tariffService.getAllForStaff();
        return ResponseEntity.ok(tariffList);
    }

    @PreAuthorize(value = "hasRole('ROLE_CLIENT')")
    @GetMapping("/getAllTariffs")
    public HttpEntity<?> getTariffsForClient(){
        List<String> tariffList=tariffService.getAllForClient();
        return ResponseEntity.ok(tariffList);
    }


    @PreAuthorize(value = "hasRole('ROLE_CLIENT')")
    @GetMapping("/getTariff/{tariffName}")
    public HttpEntity<?> getTariffInfo(@PathVariable String tariffName){
        TariffDtoForClient tariffInfo=tariffService.getTariffInfo(tariffName);
        return ResponseEntity.ok(tariffInfo);
    }


}
