package com.smart_healthcare_system.backend.asiri_dev.controller;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

        private final PaymentService paymentService;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public void createPayment(@RequestBody PaymentRequest paymentRequest) {
            paymentService.createPayment(paymentRequest);
        }
}
