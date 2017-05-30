package vertx.redis;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Server extends AbstractVerticle {

    private RedisClientExample example;

    @Override
    public void start() throws Exception {
        super.start();
        Router router = Router.router(vertx);
        router.route("/test.json").handler(this::test);
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept).listen(8090, "127.0.0.1");
        // if (example == null) {
        // synchronized (Server.class) {
        // if (example == null) {
        // example = new RedisClientExample(vertx);
        // example.set("name", new Person("hahaha", "papapa"));
        // }
        // }
        // }
        example = new RedisClientExample(vertx);

        System.err.println("start Server...");
    }

    private void test(RoutingContext context) {
        example.get("name", r -> {
            if (r.succeeded()) {
                System.out.println("result: " + r.result());
                context.response().putHeader("content-type", "application/json").end(r.toString());
            } else {
                context.response().putHeader("content-type", "application/json").end(r.toString());
            }
        });

    }
}
