package io.github.riojano0.flypromopreviewpage;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends AbstractVerticle {

   public static void main(String[] args) {
      Vertx vertx = Vertx.vertx();
      vertx.deployVerticle(new StaticResourceServerVerticle(), h -> {
         if (h.succeeded()) {
            log.info("StaticResourceServerVerticle Deployed");
         }
      });
   }
}
