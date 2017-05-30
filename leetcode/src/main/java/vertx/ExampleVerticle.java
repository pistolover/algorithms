package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class ExampleVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        try {
            super.start();
            HttpServer server = vertx.createHttpServer();
            Router router = Router.router(vertx);
            router.route("/test.json").handler(this::test);
            Future<HttpServer> httpServerFuture = Future.future();
            server.requestHandler(router::accept).listen(8090,"localhost",httpServerFuture.completer());
            System.err.println("start...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test(RoutingContext context) {
        context.response().putHeader("content-type", "application/json")
        .end("HelloWorld");
    }
}
