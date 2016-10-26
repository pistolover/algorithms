package future.guava;

import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class Guava3 {
    public static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

    public static void main(String[] args) {
        Long t = System.currentTimeMillis();
        CommonFuture f = new CommonFuture<>(new GetInteger());
        CommonFuture f3 = new CommonFuture<>(new GetBoolean());
        CommonFuture f2 = new CommonFuture<>(new GetString());
        f.call();
        f2.call();
        f3.call();
        
        
        ListenableFutureTask<Integer> create = ListenableFutureTask.create(new GetInteger());
        // ListenableFuture<Integer> f1 = service.submit(new GetInteger());
        // Futures.addCallback(f1, new FutureCallback<Integer>() {
        // @Override
        // public void onSuccess(Integer result) {
        // obj.add(result);
        // }
        //
        // @Override
        // public void onFailure(Throwable t) {
        // }
        // }, service);

        // ListenableFuture<String> f2 = service.submit(new GetString());
        // Futures.addCallback(f2, new FutureCallback<String>() {
        // @Override
        // public void onSuccess(String result) {
        // obj.add(result);
        // }
        //
        // @Override
        // public void onFailure(Throwable t) {
        //
        // }
        // }, service);
        //
        // ListenableFuture<Boolean> f3 = service.submit(new GetBoolean());
        // Futures.addCallback(f3, new FutureCallback<Boolean>() {
        // @Override
        // public void onSuccess(Boolean result) {
        // obj.add(result);
        // }
        //
        // @Override
        // public void onFailure(Throwable t) {
        //
        // }
      // }, service);
        
        while(true){
            if(f.isDone() && f2.isDone() && f3.isDone()) {
                break;
            }
        }

        System.err.println(123);
        System.err.println("    " + (System.currentTimeMillis() - t));
    }

}
