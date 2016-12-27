package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchExecute {

    private static final ExecutorService fixedTaskPool = Executors.newFixedThreadPool(50);
    private static final Map<String, String> maps = new HashMap<>();
    
    public static void main(String[] args) {
        Long t1 = System.currentTimeMillis();
       
        for (int i = 0; i < 2000; i++) {
            fixedTaskPool.execute(new Runnable() {
                @Override
                public void run() {
                    maps.put(UUID.randomUUID().toString(), "bbbbb");
                }
            });
        }

        while (maps.size() != 2000) {
            System.err.println(maps.keySet().size());
            continue;
        }
        
        System.err.println(System.currentTimeMillis() - t1);
    }

}
