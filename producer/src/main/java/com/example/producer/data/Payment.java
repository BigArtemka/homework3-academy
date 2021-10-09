package com.example.producer.data;

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
public class Payment implements Payload {
  @Min(1)
  private long senderId;
  @CardNumber
  private String senderCardNumber;
  @NotNull
  @Min(1)
  @Max(1_000_000_00)
  private long amount;
  private String comment;
}
