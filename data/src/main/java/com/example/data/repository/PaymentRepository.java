package com.example.data.repository;

import com.example.data.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>, CustomPaymentRepository {
    List<PaymentEntity> getAllBySenderId(long senderId);
}
