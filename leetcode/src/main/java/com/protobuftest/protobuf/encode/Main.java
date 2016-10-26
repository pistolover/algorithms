package com.protobuftest.protobuf.encode;

import java.io.IOException;
import java.util.Arrays;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

public class Main {

    public static void main(String[] args) {
        try {
            Codec<TInteger> codec = ProtobufProxy.create(TInteger.class);
            TInteger integer = new TInteger();
//            integer.setAge(10);
            integer.setName("ab");
            byte[] encode = codec.encode(integer);
            System.err.println(Arrays.toString(encode));
            
//            Integer a = 150;
//            Codec<Integer> code1 = ProtobufProxy.create(Integer.class);
//            byte[] encode2 = code1.encode(a);
//            System.err.println(Arrays.toString(encode2));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
