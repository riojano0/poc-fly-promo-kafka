package io.github.riojano0.flypromokafka.core.producer;

import java.util.HashMap;
import java.util.Map;

import io.github.riojano0.flypromokafka.core.model.FlyPromo;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
//@PropertySource(ignoreResourceNotFound = false, value = "classpath:common-application.yml")
public class KafkaProducerConfig {

   @Value(value = "${kafka.bootstrapAddress}")
   private String bootstrapAddress;

   @Bean
   public ProducerFactory<String, String> stringProducerFactory() {
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      return new DefaultKafkaProducerFactory<>(configProps);
   }

   @Bean("kafkaStringTemplate")
   public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> stringProducerFactory) {
      return new KafkaTemplate<String, String>(stringProducerFactory);
   }

   @Bean
   public ProducerFactory<String, FlyPromo> flyPromoProducerFactory() {
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
      return new DefaultKafkaProducerFactory<>(configProps);
   }

   @Bean("kafkaFlyPromoTemplate")
   public KafkaTemplate<String, FlyPromo> flyPromoKafkaTemplate(ProducerFactory<String, FlyPromo> flyPromoProducerFactory) {
      return new KafkaTemplate<String, FlyPromo>(flyPromoProducerFactory());
   }

}
