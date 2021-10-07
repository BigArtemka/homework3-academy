package com.example.consumer;

import com.example.consumer.data.Payment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@SpringBootApplication
public class KafkaConsumerApplication {
  private final Log logger = LogFactory.getLog(this.getClass());

  public static void main(String[] args) {
    SpringApplication.run(KafkaConsumerApplication.class, args);
  }

  @KafkaListener(groupId = "consumer", topics = "payments")
  public void listen(Payment message, ConsumerRecord<String, Payment> record, Acknowledgment acknowledgment) {
    logger.info(message);
    acknowledgment.acknowledge();
  }
}
