package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import concurrent.TestReenTrantLock.synchron;

public class TestSync {
    
    public static void main(String[] args) {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(50));
        for (int J = 0; J < 10; J++) {
            for (int i = 0; i < 500; i++) {
                executorService.submit(new Runnable() {
                     @Override
                     public void run() {
                         Sample1.increment();
                         Sample2.increment();
                     }
                 });
             }
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            System.err.println(Sample1.count);
            System.err.println(Sample2.count);
        }

    }
    

    static class Sample1 {
        private static int count = 0;

       synchronized public static void increment() {
            count++;
        }

        public int getInt() {
            return count;
        }
    }

    static class Sample2 {
        private static AtomicInteger count = new AtomicInteger(0);

        public static void increment() {
            count.getAndIncrement();
        }

        public int getInt() {
            return count.get();
        }
    }

}
