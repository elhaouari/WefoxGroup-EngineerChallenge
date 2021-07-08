package com.nelhaouari.wefoxchallenge.consumers;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;
import com.nelhaouari.wefoxchallenge.service.PaymentService;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PaymentKafkaConsumer {

  @Autowired
  private PaymentService paymentService;

  @Bean
  public Consumer<KStream<String, PaymentDTO>> onlineConsumer() {
    return kstream -> kstream.foreach((key, payment) -> {
      paymentService.handleIncomingPayment(payment);
    });
  }
}
