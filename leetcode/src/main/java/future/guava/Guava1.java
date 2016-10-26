package future.guava;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class Guava1 {

	public static void main(String[] args) {

		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

		ListenableFuture<Integer> f1 = service.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(500);
				return 1;
			}
		});

		ListenableFuture<Integer> f2 = service.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(500);
				return 2;
			}
		});

		final ListenableFuture all = Futures.allAsList(f1, f2);
		final ListenableFuture transform = Futures.transform(all, new AsyncFunction<List<Integer>, Boolean>() {

			@Override
			public ListenableFuture apply(List<Integer> input) throws Exception {
				// TODO Auto-generated method stub
				return Futures.immediateFuture(String.format("success future:%d", input.size()));
			}
		});

		AsyncRestTemplate tp = new AsyncRestTemplate();
		org.springframework.util.concurrent.ListenableFuture<ResponseEntity<Object>> response = tp
				.getForEntity("http://blog.csdn.net/pistolove", Object.class);
		ListenableFuture<ResponseEntity<Object>> listenInPoolThread = JdkFutureAdapters.listenInPoolThread(response);
		Futures.addCallback(listenInPoolThread, new FutureCallback<Object>() {
			@Override
			public void onSuccess(Object result) {
				System.err.println(result.getClass());
				System.err.printf("success", result);
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.printf("failure");
			}
		});

	}
}
