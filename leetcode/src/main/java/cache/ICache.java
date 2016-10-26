package cache;

import java.util.Map;

public abstract class ICache {

    public abstract int set(String key, Object value);

    public abstract void set(Map<String, Object> maps);

    public abstract int update(String key, Object value);

    public abstract void update(Map<String, Object> maps);

    public abstract int delete(String key);

    public abstract void delete(Map<String, Object> maps);
}
