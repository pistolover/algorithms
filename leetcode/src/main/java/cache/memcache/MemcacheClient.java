package cache.memcache;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.code.yanf4j.core.impl.StandardSocketOption;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

/**
 * 读写memcached
 */
@Component("tpCacheTemplate")
public class MemcacheClient {
    private final static Logger log = Logger.getLogger(MemcacheClient.class);
    private final static int size = 100;
    private MemcachedClient memcachedClient;
    private Map<String, Codec> protobufCodec = new HashMap<>();

    public static void main(String[] args) throws Exception {
        MemcacheClient memcacheClient = new MemcacheClient();
        Person person = new Person();
        person.setUserId(1);
        person.setUserName("zhangsan");
        memcacheClient.setProtobuf("001", person);

    }

    public MemcacheClient() throws Exception {
        String servers = "127.0.0.1:11211";
        String pool = "2";
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(servers));
        builder.setConnectionPoolSize(Integer.valueOf(pool));// 设置连接池
        builder.setSocketOption(StandardSocketOption.SO_RCVBUF, 32 * 1024);// 设置接收缓存区为32K，默认16K
        builder.setSocketOption(StandardSocketOption.SO_SNDBUF, 16 * 1024); // 设置发送缓冲区为16K，默认为8K
        builder.setSocketOption(StandardSocketOption.TCP_NODELAY, false); // 启用nagle算法，提高吞吐量，默认关闭
        builder.getConfiguration().setStatisticsServer(false);// 是否统计空闲连接
        memcachedClient = builder.build();
        memcachedClient.setOptimizeMergeBuffer(false);// 连接多个操作是否优化为mget
        memcachedClient.setEnableHeartBeat(false);
        memcachedClient.setOpTimeout(2000L);
        memcachedClient.getTranscoder().setCompressionThreshold(1024 * 50);// 设置数据压缩范围
        protobufCodec.put("Person", ProtobufProxy.create(Person.class));
    }

    /**
     * 删除key
     * @param key
     */
    public int delete(String key) {
        long begin = System.currentTimeMillis();
        try {
            memcachedClient.delete(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
        long end = System.currentTimeMillis();

        log.info("delete key:" + key + "," + (end - begin) + "ms");
        return 1;
    }

    public int setProtobuf(String key, Object value) {
        if (value == null) {
            return 1;
        }
        long begin = System.currentTimeMillis();
        try {
            String protobufValue;
            if (value instanceof String) {
                protobufValue = (String) value;
            } else {
                Class c = value.getClass();
                Codec codec = protobufCodec.get(c.getSimpleName());
                if (codec == null) {
                    codec = ProtobufProxy.create(c);
                    protobufCodec.put(c.getSimpleName(), codec);
                }
                protobufValue = new String(codec.encode(value), "ISO-8859-1");
            }
            memcachedClient.set(key, 0, protobufValue);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
        long end = System.currentTimeMillis();
        log.info("set key:" + key + "," + (end - begin) + "ms");
        return 1;
    }

    public int setProtobuf(String key, Object value, int exp) {
        if (value == null) {
            return 1;
        }
        long begin = System.currentTimeMillis();
        try {
            String protobufValue;
            if (value instanceof String) {
                protobufValue = (String) value;
            } else {
                Class c = value.getClass();
                Codec codec = protobufCodec.get(c.getSimpleName());
                if (codec == null) {
                    codec = ProtobufProxy.create(c);
                    protobufCodec.put(c.getSimpleName(), codec);
                }
                protobufValue = new String(codec.encode(value), "ISO-8859-1");
            }
            memcachedClient.set(key, exp, protobufValue);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
        long end = System.currentTimeMillis();

        log.info("set key:" + key + "," + (end - begin) + "ms");
        return 1;
    }

    public <T> T getProtobuf(String key, Class<T> c) {
        T protobufValue = null;
        long begin = System.currentTimeMillis();
        try {
            String value = memcachedClient.get(key);
            if (value != null) {
                if (c == String.class) {
                    protobufValue = (T) value;
                } else {
                    String name = c.getSimpleName();
                    Codec codec = protobufCodec.get(name);
                    if (codec == null) {
                        codec = ProtobufProxy.create(c);
                        protobufCodec.put(name, codec);
                    }
                    protobufValue = (T) codec.decode(value.getBytes("ISO-8859-1"));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        long end = System.currentTimeMillis();
        log.info("get key:" + key + "," + (end - begin) + " ms");
        return protobufValue;
    }

}
