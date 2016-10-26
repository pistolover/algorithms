package future.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class CommonFuture<V> {
    private ListenableFuture<V> f;
    private ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    
    public CommonFuture(Callable<V> c) {
        this.f = service.submit(c);
    }


    public void call() {
        Futures.addCallback(f, new FutureCallback<V>() {
            @Override
            public void onSuccess(V v) {
                System.err.println(v);
            }
            @Override
            public void onFailure(Throwable t) {
            }
        }, service);
    }
    
    public boolean isDone() {
        return f.isDone();
    }
}
