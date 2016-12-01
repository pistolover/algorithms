package future.mutiTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author liqqc
 * @param <T>
 * @param <V>
 */
public class MutiFutureTaskTemplate<T, V> {
    private static final int PoolSize = 20;

    // 带有回调机制的线程池
    private static final ListeningExecutorService service = MoreExecutors
            .listeningDecorator(Executors.newFixedThreadPool(PoolSize));

    public static <T, V> List<V> batchExec(List<T> params, BatchFuture<T, V> batchFuture) {
        if (CollectionUtils.isEmpty(params)) {
            return null;
        }
        final List<V> value = new ArrayList<V>();
        try {
            List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
            for (T t : params) {
                // 将实现了Callable的任务提交到线程池中，得到一个带有回调机制的ListenableFuture实例
                ListenableFuture<V> sfuture = service.submit(new TaskTemplate<T, V>(t, batchFuture));
                Futures.addCallback(sfuture, new FutureCallback<V>() {
                    @Override
                    public void onSuccess(V result) {
                        value.add(result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
                futures.add(sfuture);
            }
            ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
            allAsList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return value;
    }
}
