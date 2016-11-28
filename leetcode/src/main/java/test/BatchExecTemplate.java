package test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.google.common.collect.Lists;

public class BatchExecTemplate<T,V> {
    protected static Log log = LogFactory.getLog(BatchExecTemplate.class);

    //像线程池创建不必要搞一个工具类，因为代码很简单，逻辑不复杂，每个人创建的池子大小可能都不一样，不通用，最好在要用的地方直接创建
    private static final int ThreadPoolSize = 50;
    
    private static final int FixedThreadPoolSize = ThreadPoolSize / 2;

    private static final ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(ThreadPoolSize);


    //不要把一个utils类当做参数使用，因为如果是参数的话，那么这个参数是随时可以变的，而utils类里面的常量都是final的，所以使用不合理
    public static <T,V> List<V> batchExec(List<T> params, BatchExecCallBack<T,V> batchExecCallBack) throws Exception{
        //优先判空
        if(CollectionUtils.isEmpty(params)){
            return null;
        }
       
        CompletionService<V> executorCompletionService = new ExecutorCompletionService<V>(FIXED_THREAD_POOL);
        List<T> cacheParams = Lists.newArrayList();
        List<V> invokeResults = Lists.newArrayList();
        for(int i = 0; i < params.size(); i++){
            cacheParams.add(params.get(i));
            if((i != 0 && (i + 1) % FixedThreadPoolSize == 0) || i == (params.size() - 1)){
                executeTask(executorCompletionService,cacheParams,invokeResults,batchExecCallBack);
                cacheParams = Lists.newArrayList();
            }
        }
        return invokeResults;
    }

    private static <T,V> void executeTask(CompletionService<V> executorCompletionService,List<T> cacheParams,List<V> invokeResults,BatchExecCallBack<T,V> batchExecCallBack) throws Exception{
        for(T param : cacheParams){
            executorCompletionService.submit(new SingleTask<T,V>(param,batchExecCallBack));
        }
        acquireFuture(executorCompletionService, cacheParams, invokeResults, batchExecCallBack);
    }

    private static <T,V> void acquireFuture(CompletionService<V> executorCompletionService, List<T> cacheParams, List<V> invokeResults, BatchExecCallBack<T, V> batchExecCallBack) throws Exception{
        Future<V> future = null;
        List<String> failList = Lists.newArrayList();
        for(int i = 0; i < cacheParams.size(); i++) {
            try {
                future = executorCompletionService.take();
                if(future.get() == batchExecCallBack.excludeValue()){
                    continue;
                }
                invokeResults.add(future.get());
            } catch (Exception e) {
                if(future != null) {
                    future.cancel(true);
                    failList.add(e.getMessage());
                }
            }
        }
        if(CollectionUtils.isNotEmpty(failList)){
            throw new RuntimeException("BatchExecTemplate.batchExec failed because that it has single task occurs error. cause : "+failList.get(0));
        }
    }

    private static class SingleTask<T,V> implements Callable<V> {
        private T param;
        private BatchExecCallBack<T,V> batchExecCallBack;
        public SingleTask(T param,BatchExecCallBack<T,V> batchExecCallBack){
            this.param = param;
            this.batchExecCallBack = batchExecCallBack;
        }
        public V call() throws Exception {
            return batchExecCallBack.callback(param);
        }
    }

    public interface BatchExecCallBack<T,V>{
        V callback(T param);
        V excludeValue();
    }
}

