package io.github.riojano0.flypromotrackinconsumer.listener;

import io.github.riojano0.flypromokafka.core.model.FlyPromo;
import io.github.riojano0.flypromotrackinconsumer.service.FlyPromoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaListenerService {

   private final FlyPromoService flyPromoService;

   @Autowired
   public KafkaListenerService(FlyPromoService flyPromoService) {
      this.flyPromoService = flyPromoService;
   }

   // Force to read from the beginning
   @KafkaListener(
         topicPartitions = {
               @TopicPartition (
                     topic = "fly-promo",
                     partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")
               )
         },
         groupId = "group-1",
         containerFactory = "flyPromoConsumerFactory"
   )
   public FlyPromo consumerFlyPromoFromBeginning(@Payload FlyPromo flyPromo, @Headers MessageHeaders headers) {
      log.info("Initialize consume: {}", flyPromo.toString());
      flyPromoService.sendFlyPromoToSocket(flyPromo);
      return flyPromo;
   }

}
