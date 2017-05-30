package future.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class FutureTaskDemo2 {

   final static AtomicInteger atomicInteger = new AtomicInteger(0);
   final static int coresize = 3;
   final static int[] arr = {1,2,3,4,5,6,7,8,9,10};
   final static int len = arr.length;
   final static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(coresize));
    
    public static void main(String[] args) {
        int count = len / coresize; //每个线程执行下标 0-count count-2count
        final List futures = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < coresize; i++) {
            futures.add(getFuture(index, index + count));
            index+=count;
        }
        final ListenableFuture allFutures = Futures.allAsList(futures);
        try {
            allFutures.get();
            System.err.println(atomicInteger.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public static ListenableFuture<Integer> getFuture(final int i, final int j){
        ListenableFuture<Integer> furure = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int k = j;
                k = k > len?len-1:k;
                int sum = 0;
                for (int m = i; m < k; m++) {
                    sum+=arr[m];
                }
                return sum;
            }
        });
        
        Futures.addCallback(furure, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                atomicInteger.set(atomicInteger.get() + result);
            }

            @Override
            public void onFailure(Throwable t) {
                
            }
        });
        return furure;
    }
}
