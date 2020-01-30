package com.example.helloworld;

import io.reactivex.Completable;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.core.http.HttpServerRequest;

public class HelloWorld extends AbstractVerticle {

    @Override
    public Completable rxStart() {
        String target = System.getenv("TARGET");
        if (target == null) {
            target = "NOT SPECIFIED";
        }

        String port = System.getenv("PORT")
        if (port == null) {
            port = "8080";
        }

        return vertx.createHttpServer().requestHandler(request -> {
                httpServerRequest.response().setChunked(true)
                    .putHeader("content-type", "text/plain")
                    .setStatusCode(200) // OK
                    .end("Hello World: " + target);
            })
            .rxListen(Integer.parseInt(port))
            .toCompletable();
    }
}
