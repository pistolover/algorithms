package future.guava;

import java.util.concurrent.Callable;

public class GetInteger implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return getInteger();
    }
    
    public static Integer getInteger() {
        return 1;
    }

}
