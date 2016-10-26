package future.guava;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class TestListenableFuture {
    // 创建线程池
    final static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Long t1 = System.currentTimeMillis();
        // 任务1
        getIntegerTask();

        // 任务2
        getStringTask();

        // 任务3
        getBooleanTask();

        // 执行时间
        System.err.println("time: " + (System.currentTimeMillis() - t1));
    }

    private static void getBooleanTask() throws InterruptedException, ExecutionException {
        Thread.sleep(500);
        ListenableFuture<Boolean> booleanTask = service.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return true;
            }
        });

        Futures.addCallback(booleanTask, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                System.err.println("BooleanTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    private static void getStringTask() throws InterruptedException, ExecutionException {
        ListenableFuture<String> stringTask = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        });

        Futures.addCallback(stringTask, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.err.println("StringTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    private static void getIntegerTask() throws InterruptedException, ExecutionException {
        ListenableFuture<Integer> integerTask = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });

        Futures.addCallback(integerTask, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.err.println("IntegerTask: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
}
