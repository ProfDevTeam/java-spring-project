package uz.pdp.pdpspring6thlessonhomework2nd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring6thlessonhomework2nd.entity.Staff;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.ApiResponse;
import uz.pdp.pdpspring6thlessonhomework2nd.payload.StaffDto;
import uz.pdp.pdpspring6thlessonhomework2nd.service.StaffService;

import java.util.List;


@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER')")
    @PostMapping
    public HttpEntity<?> addStaff(StaffDto staffDto){
        ApiResponse apiResponse = staffService.addStaff(staffDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER','ROLE_STAFF')")
    @PutMapping("/{username}")
    public HttpEntity<?> editStaff(@PathVariable String username, StaffDto staffDto){
        ApiResponse apiResponse = staffService.editStaff(username, staffDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER','ROLE_STAFF')")
    @GetMapping("/one")
    public HttpEntity<?> getStaff(){
        ApiResponse apiResponse = staffService.getStaff();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER')")
    @GetMapping
    public HttpEntity<?> getStaffList(){
        List<Staff> staffList = staffService.getStaffList();
        return ResponseEntity.ok(staffList);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_DIRECTOR','ROLE_MANAGER')")
    @DeleteMapping("/{username}")
    public HttpEntity<?> deleteStaff(@PathVariable String username){
        ApiResponse apiResponse = staffService.deleteStaff(username);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
