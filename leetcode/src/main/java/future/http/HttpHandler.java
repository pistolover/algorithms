package future.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;

import com.google.common.util.concurrent.AtomicDouble;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;

import io.netty.util.concurrent.Future;

//计算qps和平均响应时间
//qps每秒处理
//平均响应时间：总的执行时间 除以执行次数
public class HttpHandler {

	 static Bean bean;
	
	static AtomicDouble wasteTime = new AtomicDouble(); //记录时间
	
	static AtomicInteger count =  new AtomicInteger(); //记录次数
	static AsyncRestTemplate asycTemp = new AsyncRestTemplate();
	
	static List futures = new ArrayList<>();;

	ExecutorService service;
	
	public HttpHandler(Bean b) {
		bean = b;
		service = Executors.newFixedThreadPool(bean.getConCount());
	}
	
	public void execute() {
		int seconds = bean.getRuningTime();
		while(seconds-- !=0){
			service.submit(new Task());
		}
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.err.println("平均响应时间： " + (wasteTime.doubleValue() / count.intValue()));
	}

 static class Task implements Runnable{
	 @Override
		public void run() {
				long st = System.currentTimeMillis();
				HttpMethod method = HttpMethod.GET;
				org.springframework.util.concurrent.ListenableFuture<ResponseEntity<String>> future = asycTemp.exchange(bean.getUrl(),
						method, null, String.class);
				final ListenableFuture<ResponseEntity<String>> result = JdkFutureAdapters.listenInPoolThread(future);
				futures.add(result);
				Futures.addCallback(result, new FutureCallback<ResponseEntity<String>>() {
					public void onSuccess(ResponseEntity<String> result) {
						System.out.println("time: " + (System.currentTimeMillis() - st));
						wasteTime.addAndGet((System.currentTimeMillis() - st));
						count.incrementAndGet();
					}

					public void onFailure(Throwable t) {
						System.out.println("failure: " + t);
						wasteTime.addAndGet((System.currentTimeMillis() - st));
						count.incrementAndGet();
					}
				});
			}
	
	};
}
