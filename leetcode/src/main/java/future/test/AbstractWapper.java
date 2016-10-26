package future.test;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWapper implements Wrapper {
    private Map<BaseEnum, Map<String, ?>> variablesMap = new HashMap<BaseEnum, Map<String, ?>>();

    public Map<BaseEnum, Map<String, ?>> getVariablesMap() {
        return variablesMap;
    }

    public void setVariablesMap(BaseEnum key, Map<String, ?> value) {
        if (variablesMap != null) {
            variablesMap.put(key, value);
        }
    }
}
