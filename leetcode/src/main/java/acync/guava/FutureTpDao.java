package acync.guava;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import acync.guava.ConcreateWapper.Concreate;

/**
 * 请求http的公共类，通过该类批量传入请求参数和响应类型
 * 即可异步非阻塞调用获取响应结果
 * @author liqqc
 *
 */
public class FutureTpDao {
    public AsynClientTemplete asynHttpClient;

    public FutureTpDao(){
        asynHttpClient = new AsynClientTemplete(null);
    }

    public FutureTpDao(AsyncRestTemplate tp) {
        asynHttpClient = new AsynClientTemplete(tp);
    }

    public Map<IEnum, Object> getHttpData(ConcreateWapper wapper) {
        if (wapper == null)
            return new HashMap<IEnum, Object>();
        final CountDownLatch latch = new CountDownLatch(wapper.getWrapper().size());
        final Map<IEnum, Object> result = new HashMap<IEnum, Object>();

        if (wapper.getWrapper() != null) {
            for (final Concreate wp : wapper.getWrapper()) {
                try {
                    Map<BaseRequest, ?> requestMap = wp.getRequest();
                    for (final BaseRequest tpRequestInfo : requestMap.keySet()) {
                        getHttpdata(wp, tpRequestInfo, latch, requestMap, result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                latch.await();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    private void getHttpdata(Concreate wp, BaseRequest tpRequestInfo, CountDownLatch latch,
            Map<BaseRequest, ?> requestMap, Map<IEnum, Object> result) throws Exception {
        ListenableFuture<?> statResponse = null;

        if (requestMap.get(tpRequestInfo) instanceof ParameterizedTypeReference<?>) {
            ParameterizedTypeReference<?> responseType = (ParameterizedTypeReference<?>) requestMap.get(tpRequestInfo);
            statResponse = asynHttpClient.getAsyncForObject(tpRequestInfo, responseType, wp.getVariables());
        } else if (requestMap.get(tpRequestInfo) instanceof Class<?>) {
            Class<?> responseType = (Class<?>) requestMap.get(tpRequestInfo);
            statResponse = asynHttpClient.getAsyncForObject(tpRequestInfo, responseType);
        } else {
            throw new RuntimeException("requestType error...");
        }

        addCallBack(statResponse, wp.getBaseEnum(), latch, result);
    }

    private <T> void addCallBack(ListenableFuture<T> statResponse, IEnum baseEnum, CountDownLatch latch,
            Map<IEnum, Object> result) {
        if (statResponse != null) {
            statResponse.addCallback(new CommonListenableCallBack<T>(baseEnum, result, latch));
        }
    }

}
