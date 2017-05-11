package acync.future;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.util.CollectionUtils;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

/**
 * Created by wangxue23 on 2016/11/30.
 */
public class BatchExec<T, V> {

	public static AtomicInteger num = new AtomicInteger();;
	public static AtomicInteger suNum = new AtomicInteger();
	public static AtomicInteger faNum = new AtomicInteger();

	public static <T, V> List<V> batchExec(List<T> params, BatchFuture<T, V> batchFuture,
			ListeningExecutorService service) {
		if (CollectionUtils.isEmpty(params)) {
			return null;
		}

		List<V> value = Collections.synchronizedList(new ArrayList<V>());

		try {
			List<ListenableFuture<V>> futures = new ArrayList<ListenableFuture<V>>();
			System.err.println("params: " + params.size());
			ThreadPoolExecutor executor = ThreadPoolUtil.getPoolMap().get(service.hashCode());
			int maximumPoolSize = executor.getMaximumPoolSize();
			for (T t : params) {
				while (executor.getActiveCount() >= maximumPoolSize) {
					System.out.println("线程池满了睡觉---------------");
					Thread.sleep(500);
				}
				ListenableFuture<V> sfuture = service.submit(new SingleTask<T, V>(t, batchFuture));
				num.incrementAndGet();
				Futures.addCallback(sfuture, new FutureCallback<V>() {
					@Override
					public void onSuccess(V result) {
						value.add(result);
						suNum.incrementAndGet();
					}

					@Override
					public void onFailure(Throwable t) {
						faNum.incrementAndGet();
						throw new RuntimeException(t);
					}
				});
				futures.add(sfuture);
			}
//			System.err.println("futures: " + futures.size());
			ListenableFuture<List<V>> allAsList = Futures.allAsList(futures);
			allAsList.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * 业务实现类
	 * 
	 * @param <T>
	 * @param <V>
	 */

	private static class SingleTask<T, V> implements Callable<V> {
		private volatile T param;
		private volatile BatchFuture<T, V> batchFuture;

		public SingleTask(T param, BatchFuture<T, V> batchFuture) {
			this.param = param;
			this.batchFuture = batchFuture;
		}

		@Override
		public V call() throws Exception {
			return batchFuture.callback(param);
		}
	}

	public interface BatchFuture<T, V> {
		V callback(T param) throws Exception;
	}
	
	public static void main(String[] args) {
		
		for(int m =0; m<18; m++){
		List<Integer> param = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			param.add(i);
		}
		ListeningExecutorService ocrService = ThreadPoolUtil.getOcrService();
		List<Integer> resultList = BatchExec.batchExec(param, new BatchFuture<Integer, Integer>() {
			@Override
			public Integer callback(Integer param) throws Exception {
				Thread.sleep(1);
				// System.out.println(param);
				return param++;
			}
		}, ThreadPoolUtil.getOcrService());
		System.out.println("num:" + BatchExec.num);
		System.out.println("sunum:" + BatchExec.suNum);
		System.out.println("fanum:" + BatchExec.faNum);
		System.out.println(resultList.size());
		
//		ocrService.shutdown();
		
		System.err.print("");
		}
	}

}
