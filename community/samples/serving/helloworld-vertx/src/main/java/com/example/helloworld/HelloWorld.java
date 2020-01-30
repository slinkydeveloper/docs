package com.example.helloworld;

import io.reactivex.Completable;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;

public class HelloWorld extends AbstractVerticle {

    @Override
    public Completable rxStart() {
        final String target = System.getenv().getOrDefault("TARGET", "NOT SPECIFIED");
        final String port = System.getenv().getOrDefault("PORT", "8080");

        return vertx.createHttpServer().requestHandler(request -> {
                request.response().setChunked(true)
                    .putHeader("content-type", "text/plain")
                    .setStatusCode(200) // OK
                    .end("Hello World: " + target);
            })
            .rxListen(Integer.parseInt(port))
            .toCompletable();
    }
}
