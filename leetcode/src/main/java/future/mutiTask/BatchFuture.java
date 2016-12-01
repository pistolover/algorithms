package future.mutiTask;

public interface BatchFuture<T, V> {
    V callback(T param);
}
