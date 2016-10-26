package future.guava;

import java.util.concurrent.Callable;

public class GetBoolean implements Callable<Boolean>{

    @Override
    public Boolean call() throws Exception {
        return getBoolean();
    }

    public static Boolean getBoolean() {
        return false;
    }
}
