package future.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class Guava2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService nativeExecutor = Executors.newSingleThreadExecutor();
        Future<String> nativeFuture = nativeExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 使用sleep模拟调用耗时
                TimeUnit.SECONDS.sleep(2);
                return "This is native future call.not support async callback";
            }
        });

        // Future只实现了异步，而没有实现回调.所以此时主线程get结果时阻塞.或者可以轮训以便获取异步调用是否完成
        System.err.println(nativeFuture.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
        final ListenableFuture<String> listenableFuture = service.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "this is guava future call.support async callback";
            }
        });

        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("async complete.result:   " + listenableFuture.get());
                } catch (Exception e) {
                }
            }
        }, Executors.newSingleThreadExecutor());

        // 主线程可以继续执行,异步完成后会执行注册的监听器任务.
        System.err.println("go on execute.asyn complete will callback");

        ListeningExecutorService service2 = MoreExecutors.listeningDecorator(executorService);
        final ListenableFuture<String> listenableFuture2 = service2.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.err.println("asyncThreadName:" + Thread.currentThread().getName());
                return "this is guava future call.support async callback using FutureCallback";
            }
        });
        Futures.addCallback(listenableFuture2, new FutureCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.err.println("async callback(using FutureCallback) result:" + result);
                System.err.println("execute callback threadName:" + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
