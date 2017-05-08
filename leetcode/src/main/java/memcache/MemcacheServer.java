package memcache;

import java.io.IOException;
import java.io.WriteAbortedException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class MemcacheServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 11211));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        System.err.println("start");
        
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if(next.isAcceptable()){
                    ServerSocketChannel socketChannelserver = (ServerSocketChannel) next.channel();
                    SocketChannel socketChannel = socketChannelserver.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if(next.isWritable()){
                    write(next);
                }else if(next.isReadable()){
                   read(next);
                }
            }
            
        }
    }

    private static void read(SelectionKey next) {
        SocketChannel channel = (SocketChannel) next.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        try {
            channel.read(byteBuffer);
            System.err.println(  "READ: " + new String(byteBuffer.array()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void write(SelectionKey next) {
        // TODO Auto-generated method stub
        System.err.println("write");
    }
}
