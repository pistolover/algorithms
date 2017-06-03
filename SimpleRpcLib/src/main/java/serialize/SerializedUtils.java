package serialize;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializedUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Map<String, Codec> codecMap = new HashMap<String, Codec>();

    static {
        codecMap.put("person", ProtobufProxy.create(Person.class, false));
    }

    public static Object protoBuffDecode(byte[] datas, String clazzName) {
        Object obj = null;
        try {
            Codec codec = codecMap.get(clazzName);
            obj = codec.decode(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static byte[] protoBuffEncode(Object msg, String clazzName) {
        // TODO Auto-generated method stub
        byte[] datas = null;
        try {
            Codec codec = codecMap.get(clazzName);
            datas = codec.encode(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static Object jsonDecode(byte[] datas, Class<?> clazz) {
        // TODO Auto-generated method stub
        Object obj = null;
        try {
            obj = mapper.readValue(datas, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static byte[] jsonEncode(Object msg) {
        // TODO Auto-generated method stub
        byte[] datas = null;
        try {
            datas = mapper.writeValueAsBytes(msg);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return datas;
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(20);
        p.setName("nike");
        byte[] data = protoBuffEncode(p, "person");
        Object protoBuffDecode = protoBuffDecode(data, "person");
        System.err.println(protoBuffDecode);

    }

    public static class Person {
        @Protobuf(fieldType = FieldType.STRING, required = false, order = 1)
        private String name;
        @Protobuf(fieldType = FieldType.INT32, required = false, order = 2)
        private Integer age;

        public Person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

    }
}
