package test.concurrent;

import java.util.concurrent.Callable;

import org.springframework.util.Assert;

/**
 *涓氬姟瀹炵幇绫�
 * @param <T>
 * @param <V>
 */

public class SingleTask<T, V> implements Callable<V>{
    private T param;
    private BatchFuture<T, V> batchFuture;
    public SingleTask(T param, BatchFuture<T, V> batchFuture){
    	Assert.isNull(batchFuture);
    	Assert.isNull(param);
    	this.param = param;
        this.batchFuture = batchFuture;
    }

    @Override
    public V call() throws Exception {
        return batchFuture.callback(param);
    }
}