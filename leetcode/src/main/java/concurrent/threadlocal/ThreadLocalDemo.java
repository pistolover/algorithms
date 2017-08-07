package concurrent.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalDemo {

    private static final ThreadLocal<Map<String, Object>> LOCAL = new ThreadLocal<>();

    static {
        LOCAL.set(new HashMap<String, Object>());
    }

    @SuppressWarnings("unchecked")
    public static final <T extends Object> T get(String key) {
        Map<String, Object> map = LOCAL.get();
        if (map == null) {
            return null;
        }
        return (T) map.get(key);
    }

    public static final void put(String key, Object value) {
        Map<String, Object> map = LOCAL.get();
        if (map == null) {
            return;
        }
        map.put(key, value);
    }

    public static final Map<String, Object> getCotext() {
        Map<String, Object> map = LOCAL.get();
        if (map == null) {
            return map;
        }
        return map;
    }

    public static final boolean remove(String key) {
        Map<String, Object> map = LOCAL.get();
        if (map == null) {
            return false;
        }
        map.remove(key);
        return true;
        
    }
}
