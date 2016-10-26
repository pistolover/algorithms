package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import concurrent.TestBlockingLock.BlockLock1.BlockLock2;

public class TestBlockingLock {

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 2, 3000l, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2000), new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    executor.execute(r);
                }
            });

    public static void main(String[] args) {
        while (true) {
            BlockLock1 b1 = new BlockLock1();
            BlockLock2 b2 = new BlockLock2();
            executorService.execute(b1);
            executorService.execute(b2);
        }
    }

    static class BlockLock1 extends Thread {
        @Override
        public void run() {
            System.err.println("BlockLock1: I am runing and obtain 2 minutes...");
        }

        static class BlockLock2 extends Thread {
            @Override
            public void run() {
                System.err.println("BlockLock2 : I am runing and obtain 2 minutes...");
            }
        }
    }
}
