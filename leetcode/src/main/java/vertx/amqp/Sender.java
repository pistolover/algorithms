package vertx.amqp;

/*
* Copyright 2016 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import io.vertx.amqpbridge.AmqpBridge;
import io.vertx.amqpbridge.AmqpConstants;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageProducer;
import io.vertx.core.json.JsonObject;

public class Sender {
    private static int count = 1;

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) throws Exception {
        // Runner.runExample(Sender.class);
        Vertx vertx = Vertx.vertx();
        start(vertx);
    }

    public static void start(Vertx vertx) throws Exception {
        AmqpBridge bridge = AmqpBridge.create(vertx);

        // Start the bridge, then use the event loop thread to process things
        // thereafter.
        bridge.start("127.0.0.1", 5672, res -> {
            if (!res.succeeded()) {
                System.out.println("Bridge startup failed: " + res.cause());
                return;
            }
            System.out.println("Bridge startup");
            // Set up a producer using the bridge, send a message with it.
            MessageProducer<JsonObject> producer = bridge.createProducer("myAmqpAddress");

            // Schedule sending of a message every second
            System.out.println("Producer created, scheduling sends.");
            vertx.setPeriodic(1000, v -> {
                JsonObject amqpMsgPayload = new JsonObject();
                amqpMsgPayload.put(AmqpConstants.BODY, "myStringContent" + count);
                producer.send(amqpMsgPayload);
                System.out.println("Sent message: " + count++);
            });
        });
    }

}
