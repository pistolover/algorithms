package future.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;

public class CollectionRequestWrapper extends AbstractWapper {

    private Map<BaseEnum, Map<BaseReuqest, Object>> key2RequestMap = new HashMap<BaseEnum, Map<BaseReuqest, Object>>();

    private CollectionRequestWrapper(){
        
    }

    public Map<BaseEnum, Map<BaseReuqest, Object>> getKey2RequestMap() {
        return key2RequestMap;
    }

    public Map<BaseEnum, Map<BaseReuqest, Object>> setKey2requestMap(BaseEnum key, BaseReuqest info, ParameterizedTypeReference<?> obj) {
        Map<BaseReuqest, Object> transform = new HashMap<BaseReuqest, Object>();
        if (obj instanceof ParameterizedTypeReference) {
            transform.put(info, obj);
            key2RequestMap.put(key, transform);
        }

        return key2RequestMap;
    }

    @Override
    public void wrapVariables(BaseEnum flag, Map<String, ?> variable) {
        super.setVariablesMap(flag, variable);
    }

    public static CollectionRequestWrapper getInstanec() {
        return new CollectionRequestWrapper();
    }
}
