package cache.guavacache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class GuavaCache02 {

    private Cache<String, Person> caches;

    public GuavaCache02() {
        if (caches == null) {
            //双重锁判断
            synchronized (this) {
                caches = CacheBuilder.newBuilder().
                // 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                        maximumSize(100).
                        // 设置写缓存后8秒钟过期
                        expireAfterWrite(8, TimeUnit.SECONDS).
                        // 设置缓存容器的初始容量为10
                        initialCapacity(10).
                        // 当缓存中个数大于设置个数时就会移除
                        removalListener(new RemovalListener<Object, Object>() {
                            @Override
                            public void onRemoval(RemovalNotification<Object, Object> notification) {
                                System.out.println(
                                        notification.getKey() + " was removed, cause is " + notification.getCause());
                            }
                        }).build();

                caches.put("1", new Person("1", "zhangsan"));
                caches.put("2", new Person("2", "lisi"));
                caches.put("3", new Person("3", "wangwu"));
            }
        }
    }

    public static void main(String[] args) throws ExecutionException {
        GuavaCache02 cache = new GuavaCache02();
        cache.testCache();
    }

    public void testCache() throws ExecutionException {
        Person p1 = caches.getIfPresent("1");
        Person p2 = caches.getIfPresent("2");
        Person p3 = caches.getIfPresent("3");
        System.err.println("all: " + p1 + " " + p2 + " " + p3);
        
        Thread thread  = new Thread(new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    // 过期了
                    Thread.sleep(8000);
                    System.err.println(caches.getIfPresent("1") + " Thread: " + Thread.currentThread().getName());
                    System.err.println(caches.getIfPresent("2") + " Thread: " + Thread.currentThread().getName());
                    System.err.println(caches.getIfPresent("3") + " Thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        
        
        try {
            // 过期了
            Thread.sleep(10000);
            System.err.println(caches.getIfPresent("1") + " Thread: " + Thread.currentThread().getName());
            System.err.println(caches.getIfPresent("2") + " Thread: " + Thread.currentThread().getName());
            System.err.println(caches.getIfPresent("3") + " Thread: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
