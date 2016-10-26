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
        while (true) {
            ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println("callback: " + Thread.currentThread().getName());
                    Thread.sleep(2830);
                    return 1;
                }
            });

            Futures.addCallback(future, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                }

                @Override
                public void onFailure(Throwable t) {
                    System.err.println("error");
                }
            });

            Thread.sleep(1);
        }

//        for (int i = 0; i < 10; i++) {
//            service.execute(new Runnable() {
//                public void run() {
//                    String u = "http://localhost:8080/xserver-api-mobile/vip/pay/getCheckoutCounter.json?terminalApplication=letv_leading_app&token=103b7ce99eQlzpKXKfOOEtahQafhFrge39m2FLwUoOiM9vDj9rvS6Q2VXuG4uLbrxsKrB5yYQ&mac=84730331579A&bsChannel=letv_debug&devKey=4ce35bd55e4160437be8248f7cbf2d06&vipType=9&appVersion=1.6.8&terminalBrand=letv&uid=128899391&terminalSeries=Le_Max_default&pcode=160110000&wcode=&langcode=&devId=866647020602703";
//                    try {
//                        readContentFromGet(u);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            Thread.sleep(200);
//        }
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
