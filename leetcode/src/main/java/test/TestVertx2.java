package test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

public class TestVertx2 extends AbstractVerticle{

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(TcpClientVerticle.class.getName());
    }
    
    public static class TcpClientVerticle extends AbstractVerticle {
        int i = 0;
        @Override
        public void start() throws Exception {
          vertx.createNetClient().connect(8080, "127.0.0.1", ar -> {
            if (ar.succeeded()) {
              NetSocket socket = ar.result();
              System.out.println(Thread.currentThread().getName());
              socket.handler(buffer -> {
                i++;
                System.out.println(Thread.currentThread().getName());
                System.out.println("Net client receiving: " + buffer.toString("UTF-8"));
              });
              socket.write("+1s\n");
            } else {
              ar.cause().printStackTrace();
            }
          });
        }
      }
}
