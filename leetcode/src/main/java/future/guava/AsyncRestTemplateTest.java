package future.guava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

public class AsyncRestTemplateTest {

	private static ExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));
	public static void main(String[] args) throws Exception {

		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(100);
		while(true){
			newScheduledThreadPool.schedule(new HttpTask(), 100, TimeUnit.MILLISECONDS);
		}
//		for (int i = 0; i < 100000; i++) {
//			service.submit(new HttpTask());
//		}
	}

}

class HttpTask implements Runnable {
	static AsyncRestTemplate asycTemp = new AsyncRestTemplate();

	@Override
	public void run() {
		String url = "http://api.itv.letv.com/iptv/api//golive/getMovieList.json?langcode=zh_cn";
		long st = System.currentTimeMillis();
		HttpMethod method = HttpMethod.GET;
		org.springframework.util.concurrent.ListenableFuture<ResponseEntity<String>> future = asycTemp.exchange(url,
				method, null, String.class);
		final ListenableFuture<ResponseEntity<String>> result = JdkFutureAdapters.listenInPoolThread(future);
		Futures.addCallback(result, new FutureCallback<ResponseEntity<String>>() {
			public void onSuccess(ResponseEntity<String> result) {
//				System.out.println("success result: " + result.getBody());
				System.out.println("time: " + (System.currentTimeMillis() - st));
			}

			public void onFailure(Throwable t) {
				System.out.println("failure: " + t);
			}
		});
	}
}
