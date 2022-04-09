package io.github.riojano0.flypromoflyWU.model;

import java.util.UUID;

import io.github.riojano0.flypromokafka.model.FlyPromo;

public class FlyWuFlyPromo extends FlyPromo {

   public FlyWuFlyPromo() {
      super("Fly-WU");
      this.flyPromoId = this.provider + "-" + UUID.randomUUID();
   }

}
