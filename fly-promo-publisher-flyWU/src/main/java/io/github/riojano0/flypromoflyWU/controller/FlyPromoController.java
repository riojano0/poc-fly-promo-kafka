package io.github.riojano0.flypromoflyWU.controller;

import io.github.riojano0.flypromoflyWU.model.FlyWuFlyPromo;
import io.github.riojano0.flypromoflyWU.service.FlyPromoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FlyPromoController {

   private final FlyPromoService flyPromoService;

   @Autowired
   public FlyPromoController(FlyPromoService flyPromoService) {
      this.flyPromoService = flyPromoService;
   }

   @GetMapping
   public FlyWuFlyPromo publishPromoDummy() {
      return flyPromoService.generateFlyPromo();
   }

}
