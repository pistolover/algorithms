package future.guava;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;

public class AsyncRestTemplateTest {

    public static void main(String[] args) throws Exception {
        AsyncRestTemplate asycTemp = new AsyncRestTemplate();
        String url = "http://api.itv.letv.com/iptv/api//golive/getMovieList.json?langcode=zh_cn";
        HttpMethod method = HttpMethod.GET;
        org.springframework.util.concurrent.ListenableFuture<ResponseEntity<String>> future = asycTemp.exchange(url, method, null, String.class);
        final ListenableFuture<ResponseEntity<String>> result = JdkFutureAdapters.listenInPoolThread(future);
        
//        System.err.println(result.get());
        
        Futures.addCallback(result, new FutureCallback<ResponseEntity<String>>() {
            public void onSuccess(ResponseEntity<String> result) {
                System.out.println("success result: " + result.getBody());
            }
            public void onFailure(Throwable t) {
                System.out.println("failure: " + t);
            }
        });
        Thread.sleep(1000);
    }
}
