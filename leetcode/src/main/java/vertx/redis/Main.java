package vertx.redis;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        DeploymentOptions options = new DeploymentOptions();
        options.setInstances(100);
        options.setWorker(false);
        vertx.deployVerticle(Server.class.getName(), options);
    }
}
