package io.github.riojano0.flypromotrackinconsumer.controller;

import java.util.List;

import io.github.riojano0.flypromokafka.core.model.FlyPromo;
import io.github.riojano0.flypromotrackinconsumer.service.FlyPromoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlyPromoController {

   private final FlyPromoService flyPromoService;

   @Autowired
   public FlyPromoController(FlyPromoService flyPromoService) {
      this.flyPromoService = flyPromoService;
   }

   @GetMapping("/fly-promo-list")
   @MessageMapping("/init-fly-promos")
   @SendTo("/fly-promo-list")
   public List<FlyPromo> getFlyPromos() {
      return flyPromoService.getFlyPromos();
   }

   //Used by simpMessagingTemplate
   @SendTo("/fly-promo")
   public FlyPromo flyPromo(FlyPromo flyPromo) {
      return flyPromo;
   }
}
