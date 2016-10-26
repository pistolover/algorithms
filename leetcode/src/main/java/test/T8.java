package test;

import java.io.ByteArrayInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T8 {

    private static final ExecutorService exec = new ThreadPoolExecutor(0, 10, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    static int j = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 10000000; i++) {
            exec.execute(new Runnable() {
                public void run() {
                    try {
                        ByteArrayInputStream stream = new ByteArrayInputStream("UFT-8".getBytes());
                        if(stream!=null) {
                            System.err.println(stream.read());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            System.err.println(j++ + "====" + Thread.currentThread().getState());
        }
    }
}
