package nio.jnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private int port;

    public Server(int port) {
        this.port = port;
        initServer();
    }

    public void initServer() {
        try {
            this.selector = Selector.open();
            this.serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new Acceptor());
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8080)).start();
    }

    public void run() {
        while (true) {
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if (!next.isValid()) {
                        continue;
                    }
                    dispatch(next);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey next) {
        Runnable acceptor = (Runnable) next.attachment();
        if (acceptor != null) {
            acceptor.run();
        }
    }

    public class Acceptor implements Runnable {
        public void run() {
            try {
                accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void accept() throws IOException {
        SocketChannel accept = serverSocketChannel.accept();
        accept.configureBlocking(false);
        new Handler(selector, accept);
    }

    public class Handler implements Runnable {
        private SocketChannel socketChannel;
        private SelectionKey key;
        static final int MAXIN = 8192, MAXOUT = 11240 * 1024;
        ByteBuffer readBuffer = ByteBuffer.allocate(MAXIN);
        ByteBuffer outBuffer = ByteBuffer.allocate(MAXOUT);
        static final int READING = 0;
        static final int SENDING = 1;
        int state = READING;

        public Handler(Selector selector, SocketChannel accept) {
            try {
                this.socketChannel = accept;
                this.key = accept.register(selector, SelectionKey.OP_READ);
                this.key.attach(this);
            } catch (ClosedChannelException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private void write() throws IOException {
            socketChannel.write(outBuffer);
            if (outBuffer.remaining() > 0) {
                return;
            }
            state = READING;
            key.interestOps(SelectionKey.OP_READ);
        }

        private void read() throws IOException {
            readBuffer.clear();
            int numRead;
            try {
                // 读取数据
                numRead = socketChannel.read(readBuffer);
            } catch (Exception e) {
                key.cancel();
                socketChannel.close();
                return;
            }

            if (numRead == -1) {
                socketChannel.close();
                key.cancel();
                return;
            }
            // 处理数据
            process(numRead);
        }

        private void process(int numRead) {
            byte[] data = new byte[numRead];
            System.arraycopy(readBuffer.array(), 0, data, 0, numRead);
            System.err.println(new String(data));
            outBuffer = ByteBuffer.wrap(data);
            state = SENDING;
            key.interestOps(SelectionKey.OP_WRITE);
        }

        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == SENDING) {
                    write();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
