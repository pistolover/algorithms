package acync.guava;

import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * 具体异步调用请求类
 * 可以重写其中的方法
 * @author liqqc
 *
 */
public class AsynClientTemplete extends AbstractTemplete {

    public AsynClientTemplete(AsyncRestTemplate template) {
        setTemplete(template);
    }

    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest, Class<T> responseType)
            throws Exception {
        return super.getAsyncForObject(baserequest, responseType);
    }

    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest,
            ParameterizedTypeReference<T> responseType) throws Exception {
        return super.getAsyncForObject(baserequest, responseType);
    }

    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest, Class<T> responseType,
            Map<String, ?> uriVariables) throws Exception {
        return super.getAsyncForObject(baserequest, responseType, uriVariables);
    }

    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest,
            ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws Exception {
        return super.getAsyncForObject(baserequest, responseType, uriVariables);
    }

    @Override
    void setTemplete(AsyncRestTemplate template) {
        asyncRestTemplate = template == null ? new AsyncRestTemplate() : template;
    }

}
