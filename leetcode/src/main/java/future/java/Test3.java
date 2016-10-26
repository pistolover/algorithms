package future.java;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test3 {

    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 15; i++) {
            final int s = i;
            cs.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return s;
                }
            });
        }
        
        for (int i = 0; i < 15; i++) {
            try {
                System.err.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
