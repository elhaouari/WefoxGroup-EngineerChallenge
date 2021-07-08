package com.nelhaouari.wefoxchallenge.consumers;

import com.nelhaouari.wefoxchallenge.model.Payment;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PaymentKafkaConsumer {

  @Bean
  public Consumer<KStream<String, Payment>> onlineConsumer() {
    return kstream -> kstream.foreach((key, payment) -> {
      System.out.println(payment);
    });
  }
}
