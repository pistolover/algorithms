package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TesCpu {

    public static void main(String[] args) {

        ExecutorService service = new ThreadPoolExecutor(90, 90, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        
        for (int i = 0; i < 50; i++) {
            service.submit(new Caculate());
        }
        System.err.println("1111");
    }

    static class Caculate implements Runnable {

        @Override
        public void run() {
            long sum = 0;
            for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
                sum += i;
            }
            System.err.println(sum);
        }

    }

}
