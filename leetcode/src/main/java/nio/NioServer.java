package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer(8080);
        server.listener();
        System.err.println("start succ...");
    }

    public void initServer(int port) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.err.println();
    }

    public void listener() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            System.err.println("--server--" + this.selector.selectedKeys().size());
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("发送一个请求").getBytes()));
                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] array = buffer.array();
        String msg = new String(array).trim();
        System.err.println("服务器端收到信息" + msg);
        ByteBuffer outbuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outbuffer);
    }
}
