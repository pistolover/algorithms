package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class ExampleVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ExampleVerticle());
    }

    @Override
    public void start() throws Exception {
        // TODO Auto-generated method stub
        super.start();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/test.json").handler(this::test);
        Future<HttpServer> httpServerFuture = Future.future();
        server.requestHandler(router::accept).listen(8080,"localhost",httpServerFuture.completer());
//        server.requestHandler(request -> {
//            // This handler gets called for each request that arrives on the
//            // server
//            HttpServerResponse response = request.response();
//            response.putHeader("content-type", "text/plain");
//
//            // Write to the response and end it
//            response.end("Hello World!");
//        });
//        server.listen(8080);
        System.err.println("start...");
    }

    public void test(RoutingContext context) {
        String string = context.request().params().get("id");
        System.err.println("id = " + string);
        //其它verticle处理返回结果
    }

}
