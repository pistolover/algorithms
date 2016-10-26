package acync.spring;

import java.util.concurrent.Callable;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class MyThreadPool extends ThreadPoolTaskExecutor {
    private static final long serialVersionUID = -8513447461990726745L;

    @Override
    public <T> ListenableFuture<T> submit(Callable<T> call) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(getThreadPoolExecutor());
        return service.submit(call);
    }

    @Override
    public void setCorePoolSize(int corePoolSize) {
        super.setCorePoolSize(100);
    }

    @Override
    public void setMaxPoolSize(int maxPoolSize) {
        super.setMaxPoolSize(1000);
    }

}
