package vertx.redis;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;


public class RedisClientExample{
    private static final Logger logger = LoggerFactory.getLogger(RedisClientExample.class);
	
	private RedisClient client;

	public RedisClientExample(Vertx vertx){
        String host = Vertx.currentContext().config().getString("host");
        if (host == null) {
            host = "127.0.0.1";
        }

        client = RedisClient.create(vertx, new RedisOptions().setHost(host).setAuth("redis"));
        set("key", new Person("zs", "28"));
        
        System.err.println("start RedisClientExample...");
	}

	

	public void set(String key, Person p) {
		try {
			String value = Json.prettyMapper.writeValueAsString(p);
			client.setex(key, 360, value,  r -> {
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
	    long start  = System.currentTimeMillis();
		client.get(key, r -> {
			if (r.succeeded()) {
				try {
					Person readValue = Json.prettyMapper.readValue(r.result(), Person.class);
					handler.handle(io.vertx.core.Future.succeededFuture(readValue));
					logger.info("get key waste time: " + (System.currentTimeMillis() -start));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Connection or Operation Failed " + r.cause());
			}
		});
	}
}
