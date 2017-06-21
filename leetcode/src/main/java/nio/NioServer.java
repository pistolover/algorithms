package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer(8098);
        server.listener();
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
            System.err.println("server size: " + this.selector.selectedKeys().size());
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                   accept(key);
                } else if (key.isReadable()) {
                    read(key);
                } else if(key.isWritable()){
                	write(key);
                } 
            }
        }
    }

    private void accept(SelectionKey key) {
    	 try {
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			 SocketChannel channel = server.accept();
			 channel.configureBlocking(false);
			 channel.write(ByteBuffer.wrap(new String("I am server, I send a msg").getBytes()));
			 channel.register(this.selector, SelectionKey.OP_READ);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write(SelectionKey key) {
		try {
			SocketChannel channel = (SocketChannel) key.channel();
			String str = new String("server send msg to client...");
			channel.configureBlocking(false);
			channel.write(ByteBuffer.wrap(str.getBytes()));
			System.err.println("服务器write: " + str);
			channel.register(this.selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] array = buffer.array();
        String msg = new String(array).trim();
        System.err.println("服务器read: " + msg);
		channel.register(this.selector, SelectionKey.OP_WRITE);

    }
}
