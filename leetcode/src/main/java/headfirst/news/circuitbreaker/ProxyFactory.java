package headfirst.news.circuitbreaker;

public class ProxyFactory {
    public static <T> T proxyBean(Object target){
        return (T) new CircuitBreakerInvocationHandler(target).proxy();
    }
}
