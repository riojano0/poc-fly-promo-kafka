package io.github.riojano0.flypromokafka.core.consumer;

import java.util.HashMap;
import java.util.Map;

import io.github.riojano0.flypromokafka.core.model.FlyPromo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

   @Value(value = "${kafka.bootstrapAddress}")
   private String bootstrapAddress;

   public ConsumerFactory<String, FlyPromo> consumerFactory() {
      var deserializer = new JsonDeserializer<>(FlyPromo.class);
      deserializer.setRemoveTypeHeaders(false);
      deserializer.addTrustedPackages("*");
      deserializer.setUseTypeMapperForKey(true);

      Map<String, Object> props = new HashMap<>();
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
      props.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
      props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
      props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

      return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
   }

   @Bean("flyPromoConsumerFactory")
   public ConcurrentKafkaListenerContainerFactory<String, FlyPromo> kafkaListenerContainerFactory() {
      var factory = new ConcurrentKafkaListenerContainerFactory<String, FlyPromo>();
      factory.setConsumerFactory(consumerFactory());
      return factory;
   }

}
