//package future.test;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.CountDownLatch;
//
//import org.springframework.core.ParameterizedTypeReference;
//
//import com.google.common.util.concurrent.ListenableFuture;
//
//public class FutureTpDao{
//
//    public Map<BaseEnum, Object> getHttpData(ObjectRequestWapper wapper) {
//        final Map<BaseEnum, Map<BaseReuqest, Class<?>>> resquestMap = wapper.getKey2RequestMap();
//        final Map<BaseEnum, Map<String, ?>> uriVariableMap = wapper.getVariablesMap();
//        final CountDownLatch latch = new CountDownLatch(resquestMap.keySet().size());
//        final Map<BaseEnum, Object> result = new HashMap<BaseEnum, Object>();
//        final Set<BaseEnum> keySet = resquestMap.keySet();
//
//        if (keySet != null) {
//            for (final BaseEnum key : keySet) {
//                try {
//                    Map<BaseReuqest, Class<?>> requestMap = resquestMap.get(key);
//                    for (BaseReuqest tpRequestInfo : requestMap.keySet()) {
//                        Class<?> responseType = requestMap.get(tpRequestInfo);
//                        ListenableFuture<?> statResponse = null;
//                        if (uriVariableMap != null && uriVariableMap.get(key) != null) {
////                            statResponse = this.httpClientTemplate.getAsyncForObject(tpRequestInfo, responseType, uriVariableMap.get(key));
//                        } else {
////                            statResponse = this.httpClientTemplate.getAsyncForObject(tpRequestInfo, responseType);
//                        }
//                        addCallBack(statResponse, key, result, latch);
//                    }
//                } catch (Exception e) {
//                }
//            }
//
//            try {
//                latch.await();
//            } catch (Exception e) {
//            }
//        }
//        return result;
//    }
//
//    public Map<BaseEnum, Object> getHttpData(CollectionRequestWrapper wapper){
//        final Map<BaseEnum, Map<BaseReuqest, Object>> resquestMap = wapper.getKey2RequestMap();
//        final Map<BaseEnum, Map<String, ?>> uriVariableMap = wapper.getVariablesMap();
//        final CountDownLatch latch = new CountDownLatch(resquestMap.keySet().size());
//        final Map<BaseEnum, Object> result = new HashMap<BaseEnum, Object>();
//        final Set<BaseEnum> keySet = resquestMap.keySet();
//        
//        if (keySet != null) {
//            for (final BaseEnum key : keySet) {
//                try {
//                    Map<BaseReuqest, Object> requestMap = resquestMap.get(key);
//                    for (BaseReuqest tpRequestInfo : requestMap.keySet()) {
//                        ParameterizedTypeReference<?> responseType = (ParameterizedTypeReference<?>) requestMap.get(tpRequestInfo);
//                        ListenableFuture<?> statResponse = null;
//                        if (uriVariableMap != null && uriVariableMap.get(key) != null) {
//                            statResponse = this.httpClientTemplate.getAsyncForObject(tpRequestInfo, responseType, uriVariableMap.get(key));
//                        } else {
//                            statResponse = this.httpClientTemplate.getAsyncForObject(tpRequestInfo, responseType);
//                        }
//                        addCallBack(statResponse, key, result, latch);
//                    }
//                } catch (Exception e) {
//                }
//            }
//
//            try {
//                latch.await();
//            } catch (Exception e) {
//            }
//        }
//        
//        return result;
//    }
//
//    private <T> void addCallBack(ListenableFuture<T> statResponse, BaseEnum type, Map<BaseEnum, Object> result, CountDownLatch latch) {
//        if (statResponse != null) {
//            statResponse.addCallback(new CommonListenableCallBack<T>(type, result, latch));
//        }
//    }
//
//}
