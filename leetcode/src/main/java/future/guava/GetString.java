package future.guava;

import java.util.concurrent.Callable;

public class GetString implements Callable<String>{

    @Override
    public String call() throws Exception {
        return getString();
    }
    
    public static String getString() {
        return "String";
    }

}
