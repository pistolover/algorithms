package headfirst.news.circuitbreaker;

import java.util.concurrent.ConcurrentHashMap;

public class CircuitBreakerRegister {
    private static ConcurrentHashMap<String, CircuitBreaker> breakers = new ConcurrentHashMap<String, CircuitBreaker>();

    public static CircuitBreaker get(String key){
        return breakers.get(key);
    }

    public static void putIfAbsent(String key,CircuitBreaker circuitBreaker){
        breakers.putIfAbsent(key,circuitBreaker);
    }
}
