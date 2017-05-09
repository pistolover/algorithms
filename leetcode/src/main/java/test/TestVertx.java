package test;

import io.vertx.core.Vertx;

public class TestVertx {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        Vertx vertx = Vertx.vertx();
        for (int i = 0; i < 20; i++) {
            int index = i;
            vertx.setTimer(1, t -> {
                System.out.println(index + ":" + Thread.currentThread());
            });
        }
    }
}
