package test.concurrent;

public interface BatchFuture<T,V>{
    V callback(T param) throws Exception;
}