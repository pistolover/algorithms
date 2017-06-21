package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
	// 通道管理器
	private Selector selector;
	private volatile int count = 0;
	
	
	public static void main(String[] args) throws IOException {
		NioClient client = new NioClient();
		client.init("127.0.0.1", 8098);
		client.listen();
	}

	public void init(String ip, int port) throws IOException {
		// 获得一个Socket通道
		SocketChannel channel = SocketChannel.open();
		// 设置通道为非阻塞
		channel.configureBlocking(false);
		// 获得一个通道管理器
		this.selector = Selector.open();
		// 客户端连接服务器,其实方法执行并没有实现连接
		channel.connect(new InetSocketAddress(ip, port));
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	public void listen() throws IOException {
		// 轮询访问selector
		while (true) {
			//阻塞直到有事件
			selector.select();
			// 获得selector中选中的项的迭代器
			Iterator ite = this.selector.selectedKeys().iterator();
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// 删除已选的key
				ite.remove();
				// 连接事件发生
				if (key.isConnectable()) {
					connect(key);
				} else if (key.isReadable()) {
					read(key);
				}else if(key.isWritable()) {
					write(key);
				}
			}
		}
	}

	private void write(SelectionKey key) {
		try {
			SocketChannel channel = (SocketChannel) key.channel();
			channel.configureBlocking(false);
			String str = new String("server send msg to client...");
			channel.write(ByteBuffer.wrap(str.getBytes()));
			System.err.println("客户端write: " + str +  "  " +count++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			channel.register(this.selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connect(SelectionKey key) {
		try {
			SocketChannel channel = (SocketChannel) key.channel();
            System.err.println("client size: " + this.selector.selectedKeys().size());
			// 如果正在连接，则完成连接
			if (channel.isConnectionPending()) {
				channel.finishConnect();
			}
			// 设置成非阻塞
			channel.configureBlocking(false);
			// 在这里可以给服务端发送信息哦
			channel.write(ByteBuffer.wrap(new String("I am client, I am send a request to server...").getBytes()));
			// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
			channel.register(this.selector, SelectionKey.OP_READ);
	        System.err.println("客户端connect： " +count++);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理读取服务端发来的信息 的事件
s	 */
	public void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] array = buffer.array();
        String msg = new String(array).trim();
        System.err.println("客户端read: " + msg + "  " +count++);
		channel.register(this.selector, SelectionKey.OP_WRITE);
	}

}
