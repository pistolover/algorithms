package vertx;

import io.vertx.core.Vertx;

public class Blocking {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        
        
        vertx.setPeriodic(1000, t -> {
            vertx.executeBlocking(future -> {
              try {
                Thread.sleep(200);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName());
              future.complete();
            }, r -> {});
          });

          vertx.setPeriodic(1000, t -> {
            vertx.executeBlocking(future -> {
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName());
              future.complete();
            }, r -> {});
          });
    }
}
