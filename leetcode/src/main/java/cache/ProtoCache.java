package cache;

import java.util.Map;

import com.google.common.cache.Cache;

public class ProtoCache extends ICache{

    private Cache<String, Object> cache;
    
    @Override
    public int set(String key, Object value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void set(Map<String, Object> maps) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int update(String key, Object value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(Map<String, Object> maps) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int delete(String key) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Map<String, Object> maps) {
        // TODO Auto-generated method stub
        
    }

}
