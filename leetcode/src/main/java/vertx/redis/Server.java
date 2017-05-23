package vertx.redis;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.serviceproxy.ProxyHelper;

public class Server extends AbstractVerticle {
	RedisClientExample createProxy;
	@Override
	public void start() throws Exception {
		super.start();
		Router router = Router.router(vertx);
		router.route("/test.json").handler(this::test);
		HttpServer httpServer = vertx.createHttpServer();
		httpServer.requestHandler(router::accept).listen( 8090, "127.0.0.1");
		createProxy = ProxyHelper.createProxy(RedisClientExample.class, vertx, RedisClientExample.SERVICE_ADDRESS);
		
		createProxy.set("name", new Person("hahaha", "papapa"));
		
		System.err.println("start Server...");

	}

	private void test(RoutingContext context) {
		createProxy.get("name", r->{
			if(r.succeeded()){
				System.out.println("result: " + r.result());
			}else{
				
			}
		});
		
	}
}
