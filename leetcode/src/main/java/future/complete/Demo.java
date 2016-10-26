package future.complete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.HashAttributeSet;

import org.junit.Test;

public class Demo {

    
    @Test
    public void test3(){
        Boolean is = true;
        
        change(is);
        
        System.err.println(is);
    }
    
    
    private void change(Boolean is) {
        is = false;
    }


    @Test
    public void test4(){
        Map<Integer,Integer> maps = new HashMap<Integer,Integer>();
        maps.put(1, 2);
        changeMap(maps);
        
        System.err.println(maps);
         
    }
    
    
    private void changeMap(Map<Integer, Integer> maps) {
        maps.put(2, 3);
        maps = new HashMap<>();
    }

    
    @Test
    public void test05(){
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(1);
        List<Integer> cp = ls ;
        cp.add(2);
        cp = null;
        
        System.err.println();
    }
    

    @Test
    public void t1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 100;
        });
        // future.join();
        // future.get();
        System.err.println(future.join());
    }

    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
    
    public void t2() throws InterruptedException, ExecutionException{
        CompletableFuture<Integer> compute = compute();
        System.err.println(compute.complete(100));
    }
}
