package rpc.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import rpc.netty.lib.protocol.RpcByteToMessageDecode;
import rpc.netty.lib.protocol.RpcMessageToByteEncode;
import rpc.netty.lib.response.Response;
import rpc.netty.lib.resquest.Request;

public class RpcClient extends SimpleChannelInboundHandler<Response> {

    private String host;
    private int port;
    private Response response;
    private final Object obj = new Object();

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        this.response = msg;
        synchronized (obj) {
            obj.notifyAll(); // 收到响应，唤醒线程
        }
    }

    public Response send(Request request) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // TODO Auto-generated method stub
                    ch.pipeline().addLast(new RpcMessageToByteEncode(Request.class))
                            .addLast(new RpcByteToMessageDecode(Response.class)).addLast(RpcClient.this);
                }
            }).option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().writeAndFlush(request).sync();
            synchronized (obj) {
                obj.wait(); // 未收到响应，使线程等待
            }
            if (response != null) {
                future.channel().closeFuture().sync();
            }

            return response;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        return response;
    }
}
