package concurrent.cas;

public abstract class AbstractCAS<T> {
    
    abstract T doSth();
    
    abstract boolean isDone(T t);
}
