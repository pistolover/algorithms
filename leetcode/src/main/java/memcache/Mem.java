package memcache;

public interface Mem {

    public Object get(String key);

    public void delete(String key);

    public void set(String key, Object obj, int expire);

}
