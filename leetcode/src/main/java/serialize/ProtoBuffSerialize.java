package serialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

public class ProtoBuffSerialize {

    public static void main(String[] args) throws IOException {
        start();
    }
    
    public static void start() throws IOException {
        Codec<User2> studentClassCodec = ProtobufProxy.create(User2.class, false);

        User2 u2 = new User2();
        List<User2> friends = new ArrayList<>();
        u2.setUserName("张三");
        u2.setPassWord("123456");
        u2.setUserInfo("张三是一个很牛逼的人");
        u2.setFriends(friends);

        User2 f1 = new User2();
        f1.setUserName("李四");
        f1.setPassWord("123456");
        f1.setUserInfo("李四是一个很牛逼的人");
        User2 f2 = new User2();
        f2.setUserName("王五");
        f2.setPassWord("123456");
        f2.setUserInfo("王五是一个很牛逼的人");
        friends.add(f1);
        friends.add(f2);

        Long stime_jpb_encode = System.currentTimeMillis();
        byte[] bytes = null;
        for(int i = 0; i<10; i++) {
            bytes = studentClassCodec.encode(u2);
        }
        System.out.println("jprotobuf序列化耗时：" + (System.currentTimeMillis() - stime_jpb_encode) + "ms;总大小：" + bytes.length);
        
        Long stime_jpb_decode = System.currentTimeMillis();
        User2 studentdecode = studentClassCodec.decode(bytes);
        Long etime_jpb_decode = System.currentTimeMillis();
        System.err.println("jprotobuf反序列化耗时："+ (etime_jpb_decode-stime_jpb_decode) + "ms");
    }
    
}
