package com.example.data.controller;


import com.example.data.domain.Payment;
import com.example.data.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class PaymentController {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final PaymentService service;

    @GetMapping
    List<Payment> getPayments() {
        logger.info("getPayments");
        return service.getPayments();
    }

}
