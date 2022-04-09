package io.github.riojano0.flypromotrackinconsumer;

import io.github.riojano0.flypromokafka.spring.core.annotation.EnableKafkaConsumer;
import io.github.riojano0.flypromokafka.spring.core.annotation.EnableKafkaTopics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKafkaTopics
@EnableKafkaConsumer
@SpringBootApplication
public class FlyPromoTrackingConsumerApplication {

   public static void main(String[] args) {
      SpringApplication.run(FlyPromoTrackingConsumerApplication.class, args);
   }

}
