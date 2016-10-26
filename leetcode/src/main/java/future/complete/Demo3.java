package future.complete;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Random rand = new Random();

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        Integer join = future1.join();
        System.err.println(join);
        
//        CompletableFuture<Object> f = CompletableFuture.anyOf(future1, future2);
//        
//        System.out.println(f.get());
    }

}
