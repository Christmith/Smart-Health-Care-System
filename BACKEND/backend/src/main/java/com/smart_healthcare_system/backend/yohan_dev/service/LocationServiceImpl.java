package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.dto.LocationReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.LocationRes;
import com.smart_healthcare_system.backend.yohan_dev.model.Location;
import com.smart_healthcare_system.backend.yohan_dev.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    // Add location
    @Override
    public void addLocation(LocationReq locationReq) {
        try {
            Location location = Location.builder()
                    .locationName(locationReq.getLocationName())
                    .locationDescription(locationReq.getLocationDescription())
                    .departmentId(locationReq.getDepartmentId())
                    .bookedDays(locationReq.getBookedDays())
                    .build();

            locationRepository.save(location);
            log.info("Location {} is created.", location.getLocationName());
        }
        catch (Exception e) {
            log.error("Error while creating location: {}", e.getMessage());
            throw new RuntimeException("Failed to create location");
        }
    }

    // Get All Locations
    @Override
    public List<LocationRes> getALlLocations() {
        try {
            List<Location> locations = locationRepository.findAll();
            return locations.stream().map(this::mapToLocationResponse).toList();
        } catch (Exception e) {
            log.error("Error while fetching locations: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch locations");
        }
    }

    // Get Locations By DepartmentId
    @Override
    public List<LocationRes> getLocationsByDepartmentId(String departmentId) {
        try {
            List<Location> locations = locationRepository.findByDepartmentId(departmentId);
            return locations.stream().map(this::mapToLocationResponse).toList();
        } catch (Exception e) {
            log.error("Error while fetching locations by departmentId: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch locations by departmentId");
        }
    }

    // Map Location to LocationRes
    private LocationRes mapToLocationResponse(Location location) {
        return LocationRes.builder()
                .locationId(location.getLocationId())
                .locationName(location.getLocationName())
                .locationDescription(location.getLocationDescription())
                .departmentId(location.getDepartmentId())
                .bookedDays(location.getBookedDays())
                .build();
    }
}
