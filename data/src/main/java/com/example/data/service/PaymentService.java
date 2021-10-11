package com.example.data.service;

import com.example.data.domain.Payment;
import com.example.data.entity.PaymentEntity;
import com.example.data.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final PaymentRepository repository;

    @KafkaListener(groupId = "data", topics = "completed_payments")
    public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment) {
        logger.info(message);
        var payment = new PaymentEntity();
        payment.setSenderId(message.getSenderId());
        payment.setSenderCardNumber(message.getSenderCardNumber());
        payment.setAmount(message.getAmount());
        payment.setComment(message.getComment());
        repository.save(payment);
        acknowledgment.acknowledge();
    }


    public List<Payment> getAllPayments() {
        return repository.findOrderedByIdDescLimitedTo(100).stream().map(it ->
                        new Payment(it.getSenderId(), it.getSenderCardNumber(), it.getAmount(), it.getComment()))
                .collect(Collectors.toList());
    }

    public List<Payment> getPaymentsBySenderId(long senderId) {
        return repository.getAllBySenderId(senderId).stream().map(it ->
                        new Payment(it.getSenderId(), it.getSenderCardNumber(), it.getAmount(), it.getComment()))
                .collect(Collectors.toList());
    }
}
