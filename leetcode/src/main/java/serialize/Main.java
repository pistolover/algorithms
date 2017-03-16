package serialize;

import java.io.IOException;

import serialize.fastjson.FastJsonSerialize;
import serialize.java.JavaSerialize;
import serialize.json.JsonSerialize;
import serialize.protobuf.ProtoBuffSerialize;

/**
 * @author liqqc
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ProtoBuffSerialize protoBuffSerialize = new ProtoBuffSerialize();
        protoBuffSerialize.start();

        System.err.println();
        System.err.println();
        
        JavaSerialize javaSerialize = new JavaSerialize();
        javaSerialize.start();
        System.err.println();

        JsonSerialize jsonSerialize = new JsonSerialize();
        jsonSerialize.start();
        System.err.println();

        FastJsonSerialize fastJsonSerialize = new FastJsonSerialize();
        fastJsonSerialize.start();
    }
}
