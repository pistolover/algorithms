package serialize.test;

import java.util.ArrayList;
import java.util.List;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtoStuffTest {

    public static void main(String[] args) {
        ProtoStuffTest protoStuffTest = new ProtoStuffTest();
        List<Products> products = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Products products2 = new Products();
            products.add(products2);
        }
        List<byte[]> serializeProtoStuffProductsList = protoStuffTest.serializeProtoStuffProductsList(products);

        protoStuffTest.deserializeProtoStuffDataListToProductsList(serializeProtoStuffProductsList);
    }

    public List<Products> deserializeProtoStuffDataListToProductsList(List<byte[]> bytesList) {
        if (bytesList == null || bytesList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis();
        Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
        List<Products> list = new ArrayList<Products>();
        for (byte[] bs : bytesList) {
            Products product = new Products();
            ProtostuffIOUtil.mergeFrom(bs, product, schema);
            list.add(product);
        }
        long end = System.currentTimeMillis();
        long waste = end - start;
        System.err.println("deserialze waste: " + waste);
        return list;
    }

    public List<byte[]> serializeProtoStuffProductsList(List<Products> pList) {
        if (pList == null || pList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis();
        List<byte[]> bytes = new ArrayList<byte[]>();
        Schema<Products> schema = RuntimeSchema.getSchema(Products.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] protostuff = null;
        for (Products p : pList) {
            try {
                protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
                bytes.add(protostuff);
            } finally {
                buffer.clear();
            }
        }
        long end = System.currentTimeMillis();
        long waste = end - start;
        System.err.println("serialize waste: " + waste + " size: " + bytes.size());
        return bytes;
    }
}
