package future.test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CommonListenableCallBack<T> implements ListenableFutureCallback<T> {
    private BaseEnum type;
    private Map<BaseEnum, Object> resultValue;
    private volatile CountDownLatch latch;

    public CommonListenableCallBack(BaseEnum type, Map<BaseEnum, Object> result, CountDownLatch cdl) {
        this.type = type;
        this.resultValue = result;
        this.latch = cdl;
    }

    @Override
    public void onSuccess(T result) {
        ResponseEntity<T> re = (ResponseEntity<T>) result;
        if (re.getBody() != null) {
            T body = re.getBody();
            if (type != null) {
                resultValue.put(type, body);
            }
        }
        latch.countDown();
    }

    @Override
    public void onFailure(Throwable ex) {
        latch.countDown();
    }
}