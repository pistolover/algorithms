package vertx.redis;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.types.EventBusService;
import io.vertx.serviceproxy.ProxyHelper;


@ProxyGen
@VertxGen
public class RedisClientExample extends AbstractVerticle {
	public static final String SERVICE_ADDRESS = "example.vertx.redis"; 
	public static final String SERVICE_NAME = "name.vertx.redis"; 

	
	RedisClient client;

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(RedisClientExample.class.getName());
	}

	@Override
	public void start() throws Exception {
		
		
		  // logger.info("CommentServiceImpl start");
        ProxyHelper.registerService(RedisClientExample.class, vertx, this, RedisClientExample.SERVICE_ADDRESS);
        Record record = EventBusService.createRecord(RedisClientExample.SERVICE_NAME, RedisClientExample.SERVICE_ADDRESS, RedisClientExample.class);
		
		// If a config file is set, read the host and port.
		String host = Vertx.currentContext().config().getString("host");
		if (host == null) {
			host = "192.168.205.130";
		}

		client = RedisClient.create(vertx, new RedisOptions().setHost(host));
		set("key", new Person("zs", "28"));
		
		System.err.println("start RedisClientExample...");
	}
	

	public void set(String key, Person p) {
		try {
			String value = Json.prettyMapper.writeValueAsString(p);
			client.setex(key, 60, value,  r -> {
				if (r.succeeded()) {
					System.out.println("key stored");
					client.get("key", s -> {
						System.out.println("Retrieved value: " + s.result());
					});
				} else {
					System.out.println("Connection or Operation Failed " + r.cause());
				}
			});
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void get(String key, Handler<AsyncResult<Person>> handler) {
		client.get(key, r -> {
			if (r.succeeded()) {
				try {
					Person readValue = Json.prettyMapper.readValue(r.result(), Person.class);
					handler.handle(io.vertx.core.Future.succeededFuture(readValue));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Connection or Operation Failed " + r.cause());
			}
		});
	}
}
