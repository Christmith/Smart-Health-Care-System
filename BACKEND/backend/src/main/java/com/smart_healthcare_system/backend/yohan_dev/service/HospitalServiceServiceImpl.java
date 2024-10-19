package com.smart_healthcare_system.backend.yohan_dev.service;

import com.smart_healthcare_system.backend.yohan_dev.model.Doctor;
import com.smart_healthcare_system.backend.yohan_dev.model.HospitalService;
import com.smart_healthcare_system.backend.yohan_dev.repository.DoctorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceReq;
import com.smart_healthcare_system.backend.yohan_dev.dto.HospitalServiceRes;
import com.smart_healthcare_system.backend.yohan_dev.repository.HospitalServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HospitalServiceServiceImpl implements HospitalServiceService {

    private final HospitalServiceRepository hospitalServiceRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void createService(HospitalServiceReq hospitalServiceReq) {
        // Check if the selected doctors exist
        for (String doctorId : hospitalServiceReq.getSelectedDoctors()) {
            Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
            if (doctorOptional.isEmpty()) {
                // If the doctor is not found, throw a specific exception
                throw new RuntimeException("Doctor not found");
            }
        }

        try {
            // Step 1: Create and save the HospitalService
            HospitalService hospitalService = HospitalService.builder()
                    .serviceName(hospitalServiceReq.getServiceName())
                    .serviceDescription(hospitalServiceReq.getServiceDescription())
                    .paymentOption(hospitalServiceReq.getPaymentOption())
                    .department(hospitalServiceReq.getDepartment())
                    .location(hospitalServiceReq.getLocation())
                    .selectedDays(hospitalServiceReq.getSelectedDays())
                    .selectedDoctors(hospitalServiceReq.getSelectedDoctors())
                    .timeSlots(hospitalServiceReq.getTimeSlots())
                    .build();

            hospitalServiceRepository.save(hospitalService);
            log.info("Service '{}' is created.", hospitalService.getServiceName());

            // Step 2: Update workingDays for each doctor
            updateDoctorsWorkingDays(hospitalServiceReq.getTimeSlots());

        } catch (DataAccessException e) {
            log.error("Database error while creating service: {}", e.getMessage());
            throw new RuntimeException("Database error: Failed to create service");
        } catch (Exception e) {
            log.error("Unexpected error while creating service: {}", e.getMessage());
            throw new RuntimeException("Unexpected error: Failed to create service");
        }
    }


    // Helper method to update doctor's workingDays based on the time slots
    private void updateDoctorsWorkingDays(Map<String, List<Map<String, String>>> timeSlots) {
        for (String day : timeSlots.keySet()) {
            List<Map<String, String>> doctorTimeSlots = timeSlots.get(day);

            for (Map<String, String> timeSlot : doctorTimeSlots) {
                for (String doctorId : timeSlot.keySet()) {
                    Doctor doctor = doctorRepository.findById(doctorId)
                            .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));

                    // Step 3: Update doctor's workingDays if not already present
                    if (!doctor.getWorkingDays().contains(day)) {
                        doctor.getWorkingDays().add(day);
                        doctorRepository.save(doctor);
                        log.info("Updated working days for Doctor '{}' with day '{}'", doctor.getDoctorName(), day);
                    }
                }
            }
        }
    }

    // Update service
    @Override
    public void updateService(String id, HospitalServiceReq hospitalServiceReq) {
        try {
            // Fetch the existing service
            HospitalService hospitalService = hospitalServiceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            // Build the updated service object with the new data
            HospitalService updatedService = HospitalService.builder()
                    .serviceId(hospitalService.getServiceId())
                    .serviceName(hospitalServiceReq.getServiceName())
                    .serviceDescription(hospitalServiceReq.getServiceDescription())
                    .paymentOption(hospitalServiceReq.getPaymentOption())
                    .department(hospitalServiceReq.getDepartment())
                    .location(hospitalServiceReq.getLocation())
                    .selectedDays(hospitalServiceReq.getSelectedDays())
                    .selectedDoctors(hospitalServiceReq.getSelectedDoctors())
                    .timeSlots(hospitalServiceReq.getTimeSlots())
                    .createdAt(hospitalService.getCreatedAt())
                    .build();

            // Save the updated service in the repository
            hospitalServiceRepository.save(updatedService);
            log.info("Service '{}' is updated.", updatedService.getServiceName());

            // Update workingDays for each doctor by comparing old days with new ones
            updateDoctorsWorkingDaysForUpdate(hospitalService.getTimeSlots(), hospitalServiceReq.getTimeSlots());

        } catch (DataAccessException e) {
            log.error("Database error while updating service: {}", e.getMessage());
            throw new RuntimeException("Database error: Failed to update service");
        } catch (Exception e) {
            log.error("Unexpected error while updating service: {}", e.getMessage());
            throw new RuntimeException("Unexpected error: Failed to update service");
        }
    }

    // Helper method to update doctors' workingDays during an update
    private void updateDoctorsWorkingDaysForUpdate(
            Map<String, List<Map<String, String>>> oldTimeSlots,
            Map<String, List<Map<String, String>>> newTimeSlots) {

        // Step 1: Iterate through the old time slots and remove days no longer assigned
        for (String oldDay : oldTimeSlots.keySet()) {
            List<Map<String, String>> oldDoctorTimeSlots = oldTimeSlots.get(oldDay);

            for (Map<String, String> oldTimeSlot : oldDoctorTimeSlots) {
                for (String doctorId : oldTimeSlot.keySet()) {
                    Doctor doctor = doctorRepository.findById(doctorId)
                            .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));

                    // If the old day is not in the new time slots for the doctor, remove it from workingDays
                    if (!isDoctorAssignedOnDay(doctorId, oldDay, newTimeSlots)) {
                        doctor.getWorkingDays().remove(oldDay);
                        doctorRepository.save(doctor);
                        log.info("Removed day '{}' from Doctor {}'s working days", oldDay, doctor.getDoctorName());
                    }
                }
            }
        }

        // Step 2: Add newly selected days from the updated time slots
        updateDoctorsWorkingDays(newTimeSlots);
    }

    // Helper method to check if a doctor is still assigned to a particular day in the new timeSlots
    private boolean isDoctorAssignedOnDay(String doctorId, String day, Map<String, List<Map<String, String>>> timeSlots) {
        List<Map<String, String>> doctorTimeSlots = timeSlots.get(day);

        if (doctorTimeSlots != null) {
            for (Map<String, String> timeSlot : doctorTimeSlots) {
                if (timeSlot.containsKey(doctorId)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Get all services
    @Override
    public List<HospitalServiceRes> getAllServices() {
        try {
            List<HospitalService> services = hospitalServiceRepository.findAll();
            return services.stream().map(this::mapToServiceResponse).toList();
        } catch (DataAccessException e) {
            log.error("Database error while fetching services: {}", e.getMessage());
            throw new RuntimeException("Database error: Failed to fetch services");
        } catch (Exception e) {
            log.error("Unexpected error while fetching services: {}", e.getMessage());
            throw new RuntimeException("Unexpected error: Failed to fetch services");
        }
    }

    @Override
    public HospitalServiceRes getServiceById(String id) {
        try {
            HospitalService hospitalService = hospitalServiceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found"));
            return mapToServiceResponse(hospitalService);
        } catch (DataAccessException e) {
            log.error("Database error while fetching service by id: {}", e.getMessage());
            throw new RuntimeException("Database error: Failed to fetch service by id");
        } catch (RuntimeException e) {
            log.error("Error while fetching service by id: {}", e.getMessage());
            throw e; // rethrow the exception for handling in test
        } catch (Exception e) {
            log.error("Unexpected error while fetching service by id: {}", e.getMessage());
            throw new RuntimeException("Unexpected error: Failed to fetch service by id");
        }
    }


    // Delete service
    @Override
    public void deleteService(String id) {
        try {
            HospitalService hospitalService = hospitalServiceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            // Update doctors' working days before deletion
            removeWorkingDays(hospitalService.getTimeSlots());

            hospitalServiceRepository.delete(hospitalService);
            log.info("Service '{}' is deleted.", hospitalService.getServiceName());

        } catch (DataAccessException e) {
            log.error("Database error while deleting service: {}", e.getMessage());
            throw new RuntimeException("Database error: Failed to delete service");
        } catch (Exception e) {
            log.error("Unexpected error while deleting service: {}", e.getMessage());
            throw new RuntimeException("Unexpected error: Failed to delete service");
        }
    }

    // Helper method to remove workingDays for doctors based on time slots of the deleted service
    private void removeWorkingDays(Map<String, List<Map<String, String>>> timeSlots) {
        for (String day : timeSlots.keySet()) {
            List<Map<String, String>> doctorTimeSlots = timeSlots.get(day);

            for (Map<String, String> timeSlot : doctorTimeSlots) {
                for (String doctorId : timeSlot.keySet()) {
                    Doctor doctor = doctorRepository.findById(doctorId)
                            .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorId));

                    if (doctor.getWorkingDays().contains(day)) {
                        doctor.getWorkingDays().remove(day);
                        doctorRepository.save(doctor);
                        log.info("Removed day '{}' from Doctor {}'s working days.", day, doctor.getDoctorName());
                    }
                }
            }
        }
    }

    // Map HospitalService to HospitalServiceRes
    private HospitalServiceRes mapToServiceResponse(HospitalService hospitalService) {
        return HospitalServiceRes.builder()
                .serviceId(hospitalService.getServiceId())
                .serviceName(hospitalService.getServiceName())
                .serviceDescription(hospitalService.getServiceDescription())
                .paymentOption(hospitalService.getPaymentOption())
                .department(hospitalService.getDepartment())
                .location(hospitalService.getLocation())
                .selectedDays(hospitalService.getSelectedDays())
                .selectedDoctors(hospitalService.getSelectedDoctors())
                .timeSlots(hospitalService.getTimeSlots())
                .createdAt(hospitalService.getCreatedAt())
                .updatedAt(hospitalService.getUpdatedAt())
                .build();
    }
}