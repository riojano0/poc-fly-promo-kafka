package io.github.riojano0.flypromokafka.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Publisher need to extend this class and define their own promo-id and provider
 *
 * Not abstract class to allow Jackson deserialize without error
 */
@Data
@NoArgsConstructor
@ToString
public class FlyPromo {

   protected String flyPromoId;
   protected String provider;

   public FlyPromo(String provider) {
      this.provider = provider;
   }

   @JsonDeserialize(contentAs = Country.CountryCreator.class)
   private List<Country> fromCountries;
   @JsonDeserialize(as = Country.CountryCreator.class)
   private Country toCountry;
   private BigDecimal originalPrice;
   private BigDecimal promotionalPrice;
   private LocalDateTime startDate;
   private LocalDateTime endDate;
   private Integer Stock;

}
