package com.example.data.repository;

import com.example.data.domain.Payment;
import com.example.data.entity.PaymentEntity;

import java.util.List;

public interface CustomPaymentRepository {

    List<PaymentEntity> findOrderedByIdDescLimitedTo(int limit);
}
