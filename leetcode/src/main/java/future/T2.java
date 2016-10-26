package future;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class T2 {

    @Test
    public void should_test_furture() throws Exception {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<Integer> future1 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(3000);
                System.out.println("call future 1.");
                return 1;
            }
        });

        ListenableFuture<Integer> future2 = service.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                Thread.sleep(1000);
                System.out.println("call future 2.");
                return 2;
            }
        });

        final ListenableFuture<List<Integer>> allFutures = Futures.allAsList(future1, future2);

        final ListenableFuture transform = Futures.transform(allFutures, new AsyncFunction<List<Integer>, Boolean>() {
            @Override
            public ListenableFuture apply(List<Integer> results) throws Exception {
                //调取第三方
                String rt="";
                for (Integer integer : results) {
                    rt=rt+"_"+integer;
                }
                return Futures.immediateFuture(String.format("success future:%s", rt));
            }
        });

        Futures.addCallback(transform, new FutureCallback<Object>() {
            //一旦得到结果马上回调
            public void onSuccess(Object result) {
                System.out.println(result.getClass());
                System.out.printf("success with: %s%n", result);
            }

            public void onFailure(Throwable thrown) {
                System.out.printf("onFailure%s%n", thrown.getMessage());
            }
        });

        System.out.println("----" + transform.get());
    }
}
