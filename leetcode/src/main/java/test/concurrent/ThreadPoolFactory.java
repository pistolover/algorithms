package test.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ThreadPoolFactory {

    public static final int DEFAULT_POOL_SIZE = 20;

	public static final int DEFAULT_BLOCKING_SIZE = 500;

	private static final Map<Integer,ThreadPoolExecutor> poolMap = new HashMap<Integer,ThreadPoolExecutor>();
	
	public static ListeningExecutorService getCacheService() {
		return MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
	}

	public static ListeningExecutorService getFixedService(Integer size) {
		if (size <= 0) {
			return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(DEFAULT_POOL_SIZE));
		}
		return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(size));
	}
	
	
	public static ListeningExecutorService getCommomExecutorService() {
        return getCommomExecutorService(DEFAULT_POOL_SIZE, DEFAULT_POOL_SIZE);
    }
	
	public static ListeningExecutorService getCommomExecutorService(Integer coreSize, Integer maxSize) {
	    return getCommomExecutorService(coreSize, maxSize, null);
	}

	public static ListeningExecutorService getCommomExecutorService(Integer coreSize, Integer maxSize, BlockingQueue<Runnable> workQueue) {
	    coreSize = coreSize == null ? DEFAULT_POOL_SIZE : coreSize;
		maxSize = maxSize == null ? DEFAULT_POOL_SIZE : maxSize;
		workQueue = workQueue==null ? new ArrayBlockingQueue<Runnable>(DEFAULT_BLOCKING_SIZE) : workQueue;
		ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, maxSize, 0L, TimeUnit.MILLISECONDS, workQueue, new ThreadPoolQueueFullHanlder());
		ListeningExecutorService listeningDecorator = MoreExecutors.listeningDecorator(executor);
		if(!poolMap.containsKey(listeningDecorator.hashCode())){
			poolMap.put(listeningDecorator.hashCode(), executor);
		}
		return listeningDecorator;
	}

	public static Map<Integer, ThreadPoolExecutor> getPoolmap() {
		return poolMap;
	}
	
}
