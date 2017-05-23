package vertx.redis;

import io.vertx.core.Vertx;

public class Main {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(RedisClientExample.class.getName());
		vertx.deployVerticle(Server.class.getName());
	}
}
