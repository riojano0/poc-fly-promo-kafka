package io.github.riojano0.flypromotrackinconsumer.service;

import java.util.ArrayList;
import java.util.List;

import io.github.riojano0.flypromokafka.model.FlyPromo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class FlyPromoService {

   @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;

   private final List<FlyPromo> flyPromos = new ArrayList<>();

   public void sendFlyPromoToSocket(FlyPromo flyPromo) {
      flyPromos.add(flyPromo);
      simpMessagingTemplate.convertAndSend("/fly-promo", flyPromo);
      System.out.println(flyPromo);
   }

   public List<FlyPromo> getFlyPromos() {
      return flyPromos;
   }
}
