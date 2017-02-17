package test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

/**
 * Created by wangxue23 on 2016/11/30.
 */
public final class BatchExec<T,V> {
	private static final Logger logger = LoggerFactory.getLogger(BatchExec.class); 
	private static ListeningExecutorService defaultService =  ThreadPoolFactory.getCommomExecutorService();

    //甯︽湁鍥炶皟鏈哄埗鐨勭嚎绋嬫睜
    public static <T, V> List<V> batchExec(List<T> params, BatchFuture<T, V> batchFuture, ListeningExecutorService service) {
        if(CollectionUtils.isEmpty(params)){
            return null;
        }
        
        service = service == null ? defaultService : service;
        
        final List<V> value = new ArrayList<V>();
        try{
            List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
            for(T t : params){
                //灏嗗疄鐜颁簡Callable鐨勪换鍔℃彁浜ゅ埌绾跨▼姹犱腑锛屽緱鍒颁竴涓甫鏈夊洖璋冩満鍒剁殑ListenableFuture瀹炰緥
                ListenableFuture<V> sfuture = service.submit(new SingleTask<T, V>(t, batchFuture));
                Futures.addCallback(sfuture, new FutureCallback<V>() {
                    @Override
                    public void onSuccess(V result) {
                        value.add(result);
                    }
                    @Override
                    public void onFailure(Throwable t) {
                    	logger.error("callback failed " + t);
                    }
                });
                futures.add(sfuture);
            }
            ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
            allAsList.get();
        }catch (ThreadPoolRejectedExecutionException e) {
            rejectedExecutionHandler(e.getThrowTask(), service);
            logger.warn(Thread.currentThread().getName() + " task queue full,type:" + 1 + "," + e.getMessage(), e);
        }catch(Exception e){
            logger.error("batchExec exception  " + e);
        }
        return value;
    }
    
    private static void rejectedExecutionHandler(Runnable task, ListeningExecutorService service) {
        int times = 0;
        Map<Integer, ThreadPoolExecutor> poolmap = ThreadPoolFactory.getPoolmap();
        ThreadPoolExecutor threadPoolExecutor = poolmap.get(service.hashCode());
        int maximumPoolSize = -1;
        if(threadPoolExecutor != null){
             maximumPoolSize = threadPoolExecutor.getMaximumPoolSize();
        }
        
        while (true) {
            try {
            	int activeCount = threadPoolExecutor.getActiveCount();
            	int queueSize = threadPoolExecutor.getQueue().size();
                if (activeCount >= maximumPoolSize && queueSize >= ThreadPoolFactory.DEFAULT_BLOCKING_SIZE || times >= 5) {
                    Thread.sleep(2000);
                }
                service.execute(task);
                times = 0;
                return;
            } catch (Exception e) {
                logger.warn((++times > 5 ? " EXCEPTION " : "") + Thread.currentThread().getName() + " task queue full,try times:" + times + "," + e.getMessage(), e);
            }
        }
    }
}
