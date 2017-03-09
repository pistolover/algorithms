package serialize.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

/**
 * @author liqqc
 */
public class ProtoBuffTest {
    
    private static final Codec<ListProducts> studentClassCodec = ProtobufProxy.create(ListProducts.class, false);

    public static void main(String[] args) throws IOException {
        new ProtoBuffTest().start();
    }

    public void start() throws IOException {
        List<Products> productsList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Products p = new Products();
            productsList.add(p);
        }
        ListProducts listProducts = new ListProducts();
        listProducts.setProducts(productsList);
        Long t1 = System.currentTimeMillis();
        byte[] bytes = studentClassCodec.encode(listProducts);
        System.out.println("jprotobuf waste：" + (System.currentTimeMillis() - t1) + "ms; 总大小：" + bytes.length);

        Long t2 = System.currentTimeMillis();
        studentClassCodec.decode(bytes);
        System.out.println("jprotobuf反序列化耗时：" + (System.currentTimeMillis() - t2) + "ms");
    }

}
