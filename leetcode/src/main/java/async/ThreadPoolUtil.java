package async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * Created by wangxue23 on 2017/1/3.
 */
public class ThreadPoolUtil {

    private static final int POOL_SIZE = 500;

    private static final int CORE_SIZE = 500;

    private static final int MAXI_POOL_SIZE = 500;

    private static final int QUEUE_CAPACITY = 2000;

    private static final int KEEP_ALIVE_TIME = 0;


    private static final ThreadPoolExecutor orcService =  new ThreadPoolExecutor(CORE_SIZE, MAXI_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ThreadPoolExecutor sensitiveService =  new ThreadPoolExecutor(CORE_SIZE, MAXI_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ThreadPoolExecutor semanticService = new ThreadPoolExecutor(CORE_SIZE, MAXI_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final Map<Integer, ThreadPoolExecutor> poolMap = new HashMap<Integer, ThreadPoolExecutor>();


    /**
     * 创建根据需求创建线程的线程池，且具有回调机制的线程池，没有可用线程则创建一个新线程添加到池中，未使用的线程池，60s移除
     */
    private static final ListeningExecutorService cacheService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    /**
     * 创建一个固定大小的线程池
      */
    private static final ListeningExecutorService fixedService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));

    /**
     * ocr线程池
     */
    private static final ListeningExecutorService ocrServicePro = MoreExecutors.listeningDecorator(orcService);

    /**
     * sensitive线程池
     */
    private static final ListeningExecutorService sensitiveServicePro = MoreExecutors.listeningDecorator(sensitiveService);

    /**
     * semantic线程池
     */
    private static final ListeningExecutorService semanticServicePro = MoreExecutors.listeningDecorator(semanticService);

    public static ListeningExecutorService getCacheService(){
        return cacheService;
    }

    public static ListeningExecutorService getFixedService(){
        return fixedService;
    }

    public static ListeningExecutorService getOcrService(){
        if(!poolMap.containsKey(ocrServicePro.hashCode())){
            poolMap.put(ocrServicePro.hashCode(), orcService);
        }
        return ocrServicePro;
    }

    public static ListeningExecutorService getSensitiveService(){
        if(!poolMap.containsKey(sensitiveServicePro.hashCode())){
            poolMap.put(sensitiveServicePro.hashCode(), sensitiveService);
        }
        return sensitiveServicePro;
    }

    public static ListeningExecutorService getSemanticService(){
        if(!poolMap.containsKey(semanticServicePro.hashCode())){
            poolMap.put(semanticServicePro.hashCode(), semanticService);
        }
        return semanticServicePro;
    }

    public static Map<Integer, ThreadPoolExecutor> getPoolMap(){
        return poolMap;
    }

}
