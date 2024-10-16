package com.smart_healthcare_system.backend.asiri_dev.controller;

import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentRequest;
import com.smart_healthcare_system.backend.asiri_dev.dto.PaymentResponse;
import com.smart_healthcare_system.backend.asiri_dev.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

        private final PaymentService paymentService;

        //Create Payment
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public void createPayment(@RequestBody PaymentRequest paymentRequest) {
            paymentService.createPayment(paymentRequest);
        }


        //Get All Payments
        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public List<PaymentResponse> getAllPayments(){
                return paymentService.findAllPayments();
        }

        //Update Payment
        @PutMapping("/{paymentId}")
        @ResponseStatus(HttpStatus.OK)
        public void updatePayment(@PathVariable String paymentId, @RequestBody PaymentRequest paymentRequest) {
                paymentService.updatePayment(paymentId, paymentRequest);
        }


}
