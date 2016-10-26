package serialize;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ProtoBuffSerialize protoBuffSerialize = new ProtoBuffSerialize();
        protoBuffSerialize.start();

        JavaSerialize javaSerialize = new JavaSerialize();
        javaSerialize.start();

        JsonSerialize jsonSerialize = new JsonSerialize();
        jsonSerialize.start();
        
        FastJsonSerialize fastJsonSerialize = new FastJsonSerialize();
        fastJsonSerialize.start();
    }
}
