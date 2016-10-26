package future.java;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test1 {

    public static void main(String[] args) {
        Callable<Integer> c = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(1000);
            }
        };
        
        FutureTask<Integer> f = new FutureTask<Integer>(c);
        new Thread(f).start();
        
        try {
            Thread.sleep(1000);
            System.err.println(f.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
