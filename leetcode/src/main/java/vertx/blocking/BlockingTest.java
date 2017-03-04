package vertx.blocking;

import io.vertx.core.Vertx;

public class BlockingTest {

    private Vertx vertx;

    public BlockingTest() {
        vertx = Vertx.vertx();
    }

    public static void main(String[] args) {
        BlockingTest blockingTest = new BlockingTest();
        blockingTest.testBlocking();
    }

    public void testBlocking() {
        for (int i = 0; i < 1; i++) {
            vertx.executeBlocking(future -> {
                try {
                    Thread.sleep(1000);
                    System.err.println(Thread.currentThread().getName() + "   A");
                    System.err.println(future);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                future.complete("good");
            }, re -> {
                if(re.succeeded()){
                    System.err.println("ok" + "  " + re.result());
                }else{
                    System.err.println("fail");
                }
            });
        }

    }
}
