package concurrent.cas;

public class CASUtils<T> {

    public static final int maxlooptime = 10000;

    public static final int sleeptime = 10;

    public T cas(AbstractCAS<T> object, int sleeptime, int looplimit) {
        int index = 0;
        while (index < looplimit) {
            T t = object.doSth();
            if (object.isDone(t)) {
                return t;
            }
            if (sleeptime != 0) {
                try {
                    Thread.sleep(sleeptime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            index++;
        }
        throw new RuntimeException("error");
    }
}
