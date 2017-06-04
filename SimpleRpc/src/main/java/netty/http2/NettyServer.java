package netty.http2;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class NettyServer {

    
    public static void main(String[] args) throws InterruptedException {
        NettyServer nettyServer = new NettyServer();
        nettyServer.run(8080);
        
    }
    
    public void run(final int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)  throws Exception {
                            ch.pipeline().addLast(new  HttpRequestDecoder())
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new  HttpResponseEncoder())
                                            .addLast(new  ChunkedWriteHandler())
                                    .addLast(new HttpHandler());
                        }
                    });
            ChannelFuture future = b.bind(new InetSocketAddress(port)).sync();
            
            
            future.channel().closeFuture().sync();//阻塞处理，等待服务端链路关闭之后main函数才退出
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
