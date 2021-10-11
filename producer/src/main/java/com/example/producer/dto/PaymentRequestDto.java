package com.example.producer.dto;

import com.example.producer.validation.CardNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDto {
    @CardNumber
    private String senderCardNumber;
    @NotNull
    @Min(1)
    @Max(1_000_000_00)
    private long amount;
    private String comment;
}
