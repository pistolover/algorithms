package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class TestHttp {

    static ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true)
            .setNameFormat("ListenableFutureAdapter-thread-%d").build();
    static ListeningExecutorService service = MoreExecutors
            .listeningDecorator(Executors.newCachedThreadPool(threadFactory));

    public static void main(String[] args) throws IOException, InterruptedException {
//        while (true) {
//            ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
//                @Override
//                public Integer call() throws Exception {
//                    System.out.println("callback: " + Thread.currentThread().getName());
//                    Thread.sleep(2830);
//                    return 1;
//                }
//            });
//
//            Futures.addCallback(future, new FutureCallback<Integer>() {
//                @Override
//                public void onSuccess(Integer result) {
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    System.err.println("error");
//                }
//            });
//
//            Thread.sleep(1);
//        }

        for (int i = 0; i < 200; i++) {
            service.execute(new Runnable() {
                public void run() {
                    String u = "http://blog.csdn.net/pistolove/article/details/73610588";
                    try {
                        readContentFromGet(u);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread.sleep(100);
        }
    }

    public static void readContentFromGet(String url) throws IOException {
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        System.out.println("=============================");
        System.out.println("Contents of get request");
        System.out.println("=============================");
        String lines;
        // while ((lines = reader.readLine()) != null) {
        // System.out.println(lines);
        // }
        reader.close();
        connection.disconnect();
        System.out.println("=============================");
        System.out.println("Contents of get request ends");
        System.out.println("=============================");
    }
}
