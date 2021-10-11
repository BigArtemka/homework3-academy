package com.example.data.controller;


import com.example.data.domain.JwtAuthentication;
import com.example.data.domain.Payment;
import com.example.data.service.PaymentService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class PaymentController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final PaymentService service;

    @GetMapping
    ResponseEntity<List<Payment>> getPayments(@RequestHeader("Authn") String authn) {
        logger.info("getPayments");
        final var userData = new Gson().fromJson(new String(Base64.getDecoder().decode(authn), StandardCharsets.UTF_8),
                JwtAuthentication.class);
        if (userData.getAuthorities().contains("ROLE_USER"))
            return ResponseEntity.ok(service.getPaymentsBySenderId(userData.getUserId()));
        if (userData.getAuthorities().contains("ROLE_ADMIN"))
            return ResponseEntity.ok(service.getAllPayments());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

}
