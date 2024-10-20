package com.smart_healthcare_system.backend.chamath_dev.controller;



import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerRequest;
import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerUpdateRequest;
import com.smart_healthcare_system.backend.chamath_dev.dto.CustomerResponse;
import com.smart_healthcare_system.backend.chamath_dev.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // Create Customer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);
    }

    // Get Customer by Email
    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    // Update Customer by Email
    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable String email, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        customerService.updateCustomer(email, customerUpdateRequest);
    }

    // Delete Customer by Email
    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String email) {
        customerService.deleteCustomer(email);
    }
}

