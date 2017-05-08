package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorTest {

    public static void main(String[] args) {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8080));
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.err.println("start...");
            while(true){
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if(next.isAcceptable()){
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) next.channel();
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    }else if(next.isReadable()){
                        read(next);
                    }
                    
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void read(SelectionKey next) {
        SocketChannel channel = (SocketChannel) next.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            channel.read(byteBuffer);
            byte[] array = byteBuffer.array();
            System.err.println(new String(array));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void read() {
        // TODO Auto-generated method stub
    }
}
