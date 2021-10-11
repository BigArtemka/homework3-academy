package com.example.aggregator.controller;

import com.example.aggregator.data.PaymentWithUsername;
import com.example.aggregator.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CommonsLog
public class PaymentController {
    private final PaymentService paymentService;


    @GetMapping("/payments")
    public List<PaymentWithUsername> statistic(@RequestHeader("Authn") String authn) {
        return paymentService.statistic(authn);
    }
}
