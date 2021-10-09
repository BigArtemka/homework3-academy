package com.example.producer.controller;


import com.example.producer.data.Payment;
import com.example.producer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/payments")
    public Payment send(@RequestBody @Valid Payment payment) {
        return service.send(payment);
    }
}
