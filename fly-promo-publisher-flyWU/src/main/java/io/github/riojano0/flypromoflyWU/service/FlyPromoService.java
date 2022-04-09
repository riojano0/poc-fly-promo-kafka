package io.github.riojano0.flypromoflyWU.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.github.riojano0.flypromoflyWU.model.CountryProvider;
import io.github.riojano0.flypromoflyWU.model.FlyWuFlyPromo;
import io.github.riojano0.flypromokafka.core.model.Country;
import io.github.riojano0.flypromokafka.core.model.FlyPromo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlyPromoService {

   private static final Map<Integer, BigDecimal[]> PROMOTIONAL_PRICES =
         Map.of(
               0, new BigDecimal[] { BigDecimal.valueOf(100), BigDecimal.valueOf(10) },
               1, new BigDecimal[] { BigDecimal.valueOf(100), BigDecimal.valueOf(30) },
               2, new BigDecimal[] { BigDecimal.valueOf(100), BigDecimal.valueOf(50) },
               3, new BigDecimal[] { BigDecimal.valueOf(100), BigDecimal.valueOf(90) }
               );
   private static final Random RANDOM = new Random();

   private final KafkaTemplate<String, FlyPromo> kafkaTemplate;

   @Autowired
   public FlyPromoService(@Qualifier("kafkaFlyPromoTemplate") KafkaTemplate<String, FlyPromo> kafkaTemplate) {
      this.kafkaTemplate = kafkaTemplate;
   }

   public FlyWuFlyPromo generateFlyPromo() {
      FlyWuFlyPromo flyWuFlyPromo = new FlyWuFlyPromo();
      CountryProvider[] values = CountryProvider.values();
      Country toCountry = values[RANDOM.nextInt(values.length)];
      flyWuFlyPromo.setToCountry(toCountry);

      List<Country> fromCountries = new ArrayList<>();
      do {
         for (int i = 0; i < 2; i++) {
            Country fromCountry = values[RANDOM.nextInt(values.length)];
            if (fromCountry != toCountry) {
               fromCountries.add(fromCountry);
            }
         }
      } while (fromCountries.isEmpty());
      flyWuFlyPromo.setFromCountries(fromCountries);

      BigDecimal[] bigDecimals = PROMOTIONAL_PRICES.get(RANDOM.nextInt(PROMOTIONAL_PRICES.size()));

      flyWuFlyPromo.setOriginalPrice(bigDecimals[0]);
      flyWuFlyPromo.setPromotionalPrice(bigDecimals[1]);
      flyWuFlyPromo.setStock(10);
      LocalDateTime now = LocalDateTime.now();
      flyWuFlyPromo.setStartDate(now);
      flyWuFlyPromo.setEndDate(now.plusDays(1));

      ListenableFuture<SendResult<String, FlyPromo>> future = kafkaTemplate.send("fly-promo", flyWuFlyPromo);

      future.addCallback(
            result -> log.info("Complete Fly Promo"),
            result -> log.error("Unable to send promo", result.getCause()));

      return flyWuFlyPromo;
   }

}
