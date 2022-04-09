package io.github.riojano0.flypromoflyWU.model;

import io.github.riojano0.flypromokafka.core.model.Country;

public enum CountryProvider implements Country {

   ARGENTINA(() -> "ARG"),
   COLOMBIA(() -> "COL"),
   BRAZIL(() -> "BAR"),
   MEXICO(() -> "MEX"),
   CHILE (() -> "CHL");

   private final Country country;

   CountryProvider(Country country) {
      this.country = country;
   }

   @Override
   public String getISO3() {
      return country.getISO3();
   }
}
