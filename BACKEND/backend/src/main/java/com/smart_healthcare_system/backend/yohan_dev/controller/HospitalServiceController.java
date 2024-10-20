package com.smart_healthcare_system.backend.yohan_dev.controller;

import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceRes;
import com.smart_healthcare_system.backend.yohan_dev.service.HospitalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class HospitalServiceController {
    private final HospitalServiceService hospitalServiceService;

    // Create Service
    @PostMapping
    public ResponseEntity<String> createService(@RequestBody HospitalServiceReq hospitalServiceReq) {
        try {
            hospitalServiceService.createService(hospitalServiceReq);
            return new ResponseEntity<>("Service created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get All Services
    @GetMapping
    public ResponseEntity<List<HospitalServiceRes>> getAllServices() {
        try {
            List<HospitalServiceRes> services = hospitalServiceService.getAllServices();
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Service By ServiceId
    @GetMapping("/{serviceId}")
    public ResponseEntity<HospitalServiceRes> getServiceByServiceId(@PathVariable String serviceId) {
        try {
            HospitalServiceRes service = hospitalServiceService.getServiceById(serviceId);
            return new ResponseEntity<>(service, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update Service By ServiceId
    @PutMapping("/{serviceId}")
    public ResponseEntity<String> updateServiceByServiceId(@PathVariable String serviceId, @RequestBody HospitalServiceReq hospitalServiceReq) {
        try {
            hospitalServiceService.updateService(serviceId, hospitalServiceReq);
            return new ResponseEntity<>("Service updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Service By ServiceId
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<String> deleteServiceByServiceId(@PathVariable String serviceId) {
        try {
            hospitalServiceService.deleteService(serviceId);
            return new ResponseEntity<>("Service deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
