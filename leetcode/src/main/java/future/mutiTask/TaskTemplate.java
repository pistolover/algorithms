package future.mutiTask;

import java.util.concurrent.Callable;

/**
 *业务实现类
 * @param <T>
 * @param <V>
 */

public class TaskTemplate<T, V> implements Callable<V>{
    private T param;
    private BatchFuture<T, V> batchFuture;
    public TaskTemplate(T param, BatchFuture<T, V> batchFuture){
        this.param = param;
        this.batchFuture = batchFuture;
    }

    @Override
    public V call() throws Exception {
        return batchFuture.callback(param);
    }
}

