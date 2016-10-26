//package future.test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ObjectRequestWapper extends AbstractWapper{
//    private Map<BaseEnum, Map<BaseReuqest, Class<?>>> key2RequestMap = new HashMap<BaseEnum, Map<BaseReuqest, Class<?>>>();
//
//    private ObjectRequestWapper(){
//    }
//
//    public Map<BaseEnum, Map<BaseReuqest, Class<?>>> setKey2requestMap(BaseEnum key, BaseReuqest info, Class<?> clazz) {
//        Map<BaseReuqest, Class<?>> transform = new HashMap<BaseReuqest, Class<?>>();
//        transform.put(info, clazz);
//        key2RequestMap.put(key, transform);
//        return key2RequestMap;
//    }
//
//    public Map<BaseEnum, Map<BaseReuqest, Class<?>>> getKey2RequestMap() {
//        return key2RequestMap;
//    }
//
//    @Override
//    public void wrapVariables(BaseEnum flag, Map<String, ?> variable) {
//        super.setVariablesMap(flag, variable);
//    }
//
//    public static ObjectRequestWapper getInstance() {
//        return new ObjectRequestWapper();
//    }
//}
