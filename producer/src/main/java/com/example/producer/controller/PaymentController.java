package com.example.producer.controller;


import com.example.producer.data.JwtAuthentication;
import com.example.producer.data.Payment;
import com.example.producer.service.PaymentService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/payments")
    public ResponseEntity<Payment> send(@RequestBody @Valid Payment payment, @RequestHeader("Authn") String authn) {
        final var userData = new Gson().fromJson(new String(Base64.getDecoder().decode(authn), StandardCharsets.UTF_8),
                JwtAuthentication.class);
        if (userData.getAuthorities().contains("ROLE_USER"))
        return ResponseEntity.ok(service.send(new Payment(userData.getUserId(), payment.getSenderCardNumber(),
                payment.getAmount(), payment.getComment())));
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
}
