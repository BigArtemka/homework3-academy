package com.example.aggregator.service;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UserClient;
import com.example.aggregator.data.Payment;
import com.example.aggregator.data.PaymentWithUsername;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final DataClient dataClient;
    private final UserClient userClient;

    public List<PaymentWithUsername> statistic(String authn) {
        final var payments = dataClient.getPayments(authn);
        final var userIds = payments.stream().map(Payment::getSenderId).collect(Collectors.toSet());
        final var users = userClient.getIdNameMap(userIds, authn);
        return payments.stream().map(p ->
                new PaymentWithUsername(users.get(p.getSenderId()), p.getSenderCardNumber(),
                        p.getAmount(), p.getComment())).collect(Collectors.toList());
    }
}
