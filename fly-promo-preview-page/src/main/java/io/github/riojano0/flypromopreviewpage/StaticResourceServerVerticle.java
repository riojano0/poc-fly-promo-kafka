package io.github.riojano0.flypromopreviewpage;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class StaticResourceServerVerticle extends AbstractVerticle {

   @Override
   public void start(Promise<Void> startPromise) throws Exception {
      Router router = Router.router(vertx);
      router.route("/*").handler(StaticHandler.create("static"));

      vertx.createHttpServer().requestHandler(router)
           .listen(config().getInteger("http:port", 3000),
              result -> {
                  if (result.succeeded()) {
                     startPromise.complete();
                  } else {
                     startPromise.fail(result.cause());
                  }
              });
   }

}
