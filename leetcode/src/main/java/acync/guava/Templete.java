package acync.guava;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * 异步调用http通用接口
 * @author liqqc
 *
 */
public interface Templete {
    <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest, Class<T> responseType) throws Exception;

    <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest,
            ParameterizedTypeReference<T> responseType) throws Exception;

    <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest, Class<T> responseType,
            Map<String, ?> uriVariables) throws Exception;

    <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baserequest,
            ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws Exception;
}