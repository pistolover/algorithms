package future.guava.listener;

import java.util.concurrent.Callable;

import com.google.common.util.concurrent.ListenableFutureTask;

public class GeneralRun<V> {

    private ListenableFutureTask<V> futureTask;
    
    GeneralRun(Callable<V> callable) {
       this.futureTask = ListenableFutureTask.create(callable);
    }
    
    public GeneralRun(Runnable run, V result) {
        this.futureTask = ListenableFutureTask.create(run, result);
    }

    
}
