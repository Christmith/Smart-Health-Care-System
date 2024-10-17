package com.smart_healthcare_system.backend.yohan_dev.controller;

import com.smart_healthcare_system.backend.yohan_dev.dto.LocationReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.LocationRes;
import com.smart_healthcare_system.backend.yohan_dev.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    //Add location
    @PostMapping
    public ResponseEntity<String> addLocation(@RequestBody LocationReq locationReq) {
        try{
            locationService.addLocation(locationReq);
            return new ResponseEntity<>("Location added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add location", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get all locations
    @GetMapping
    public ResponseEntity<List<LocationRes>> getAllLocations() {
        try {
            List<LocationRes> locations = locationService.getALlLocations();
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get location by departmentId
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<LocationRes>> getLocationByDepartmentId(@PathVariable String departmentId) {
        try {
            List<LocationRes> locations = locationService.getLocationsByDepartmentId(departmentId);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
