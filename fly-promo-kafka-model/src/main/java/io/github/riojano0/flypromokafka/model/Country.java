package io.github.riojano0.flypromokafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface Country {

   String getISO3();

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   class CountryCreator implements Country {

      private String iso3;

      @Override
      public String getISO3() {
         return iso3;
      }
   }
}
