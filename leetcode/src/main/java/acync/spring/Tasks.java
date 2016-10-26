package acync.spring;

import java.util.concurrent.Callable;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class Tasks {

    private static MyThreadPool myThreadPool = new MyThreadPool();;

    public static void main(String[] args) {

        ListenableFuture<Integer> submit = myThreadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(200);
                return 1;
            }
        });

        Futures.addCallback(submit, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.err.println(result);
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

}
