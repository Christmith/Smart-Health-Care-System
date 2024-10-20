package com.smart_healthcare_system.backend.chamath_dev.service;

import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerRequest;
import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerUpdateRequest;
import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerResponse;
import com.smart_healthcare_system.backend.chamath_dev.model.Customer;
import com.smart_healthcare_system.backend.chamath_dev.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Add Customer
    public void createCustomer(CustomerRequest customerRequest) {
        try {
            Customer customer = Customer.builder()
                    .fullName(customerRequest.getFullName())
                    .dateOfBirth(customerRequest.getDateOfBirth())
                    .gender(customerRequest.getGender())
                    .phoneNumber(customerRequest.getPhoneNumber())
                    .emergencyContact(customerRequest.getEmergencyContact())
                    .nicNumber(customerRequest.getNicNumber())
                    .email(customerRequest.getEmail())
                    .password(customerRequest.getPassword()) // Ensure to hash the password before saving
                    .medicalHistory(customerRequest.getMedicalHistory())
                    .allergies(customerRequest.getAllergies())
                    .bloodType(customerRequest.getBloodType())
                    .previousTreatments(customerRequest.getPreviousTreatments())
                    .build();

            customerRepository.save(customer);
            log.info("Customer {} is created.", customer.getEmail());
        } catch (Exception e) {
            log.error("Error while creating customer: {}", e.getMessage());
            throw new ResponseStatusException(NOT_FOUND, "Error while creating customer");
        }
    }

    // Get Customer by Email
    public CustomerResponse getCustomerByEmail(String email) {
        try {
            Customer customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Customer not found"));
            return mapToCustomerResponse(customer);
        } catch (Exception e) {
            log.error("Error while fetching customer details: {}", e.getMessage());
            throw new ResponseStatusException(NOT_FOUND, "Customer not found");
        }
    }

    // Update Customer by Email
    public void updateCustomer(String email, CustomerUpdateRequest customerUpdateRequest) {
        try {
            Customer existingCustomer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Customer not found"));

            existingCustomer.setFullName(customerUpdateRequest.getFullName());
            existingCustomer.setDateOfBirth(customerUpdateRequest.getDateOfBirth());
            existingCustomer.setPhoneNumber(customerUpdateRequest.getPhoneNumber());
            existingCustomer.setEmergencyContact(customerUpdateRequest.getEmergencyContact());
            existingCustomer.setNicNumber(customerUpdateRequest.getNicNumber());
            existingCustomer.setMedicalHistory(customerUpdateRequest.getMedicalHistory());
            existingCustomer.setAllergies(customerUpdateRequest.getAllergies());
            existingCustomer.setPreviousTreatments(customerUpdateRequest.getPreviousTreatments());
            // Ensure to hash the password if updating it


            customerRepository.save(existingCustomer);
            log.info("Customer {} is updated.", email);
        } catch (Exception e) {
            log.error("Error while updating customer: {}", e.getMessage());
            throw new ResponseStatusException(NOT_FOUND, "Error while updating customer");
        }
    }

    // Delete Customer by Email
    public void deleteCustomer(String email) {
        try {
            Customer customer = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Customer not found"));

            customerRepository.delete(customer);
            log.info("Customer {} is deleted.", email);
        } catch (Exception e) {
            log.error("Error while deleting customer: {}", e.getMessage());
            throw new ResponseStatusException(NOT_FOUND, "Error while deleting customer");
        }
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .fullName(customer.getFullName())
                .dateOfBirth(customer.getDateOfBirth())
                .gender(customer.getGender())
                .phoneNumber(customer.getPhoneNumber())
                .emergencyContact(customer.getEmergencyContact())
                .nicNumber(customer.getNicNumber())
                .email(customer.getEmail())
                .medicalHistory(customer.getMedicalHistory())
                .allergies(customer.getAllergies())
                .bloodType(customer.getBloodType())
                .previousTreatments(customer.getPreviousTreatments())
                .build();
    }
}
