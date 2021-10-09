package com.example.aggregator.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentWithUsername {
    private String username;
    private String cardNumber;
    private long amount;
    private String comment;
}
