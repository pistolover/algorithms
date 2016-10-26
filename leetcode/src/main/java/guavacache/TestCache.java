package guavacache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TestCache {

    @Test
    public void t1() throws ExecutionException {
        final List<Person> list = new ArrayList<Person>(5);

        list.add(new Person("1", "zhangsan"));

        list.add(new Person("2", "lisi"));

        list.add(new Person("3", "wangwu"));

        LoadingCache<String, Person> caches = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100).build(new CacheLoader<String, Person>() {

                    @Override
                    public Person load(String key) throws Exception {
                        System.out.println(key + " load in cache");
                        return getPerson(key);
                    }

                    private Person getPerson(String key) throws ExecutionException {
                        System.out.println(key + " query");
                        for (Person p : list) {
                            if (p.getId().equals(key)){
                                System.err.println("   "+ p);
                                return p;
                            }
                        }
                        return null;
                    }
                });

        caches.get("1");
        caches.get("2");
        caches.get("3");

        System.out.println("======= sencond time  ==========");

        System.err.println(caches.get("1"));
        System.err.println(caches.get("2"));
        System.err.println(caches.get("3"));
    }

    
}
