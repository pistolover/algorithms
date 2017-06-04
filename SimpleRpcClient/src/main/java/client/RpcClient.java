package client;

import java.net.URI;
import java.net.URISyntaxException;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpVersion;
import request.Request;
import response.Response;
import serialize.RpcByteToMessageDecode;
import serialize.RpcMessageToByteEncode;

public class RpcClient extends SimpleChannelInboundHandler<Response> {

    private String host;
    private int port;
    private Response response;
    private final Object obj = new Object();

    private ByteBufToBytes reader;  

    
    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
        
        if (msg instanceof HttpResponse) {  
            HttpResponse response = (HttpResponse) msg;  
            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));  
            if (HttpHeaders.isContentLengthSet(response)) {  
                reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(response));  
            }  
        }  
  
        if (msg instanceof HttpContent) {  
            HttpContent httpContent = (HttpContent) msg;  
            ByteBuf content = httpContent.content();  
            reader.reading(content);  
            content.release();  
  
            if (reader.isEnd()) {  
                String resultStr = new String(reader.readFull());  
                System.out.println("Server said:" + resultStr);  
                ctx.close();  
            }  
        }  
        
        
//        this.response = msg;
//        synchronized (obj) {
//            obj.notifyAll(); // 收到响应，唤醒线程
//        }
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            try {
                //HTTP 服务
                URI uri = new URI("http://127.0.0.1:2203");  
                String msg = "Are you ok?";  
                DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,  
                        uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes()));  
  
                // 构建http请求  
                defaultFullHttpRequest.headers().set(HttpHeaders.Names.HOST, host);  
                defaultFullHttpRequest.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);  
                defaultFullHttpRequest.headers().set(HttpHeaders.Names.CONTENT_LENGTH, defaultFullHttpRequest.content().readableBytes());  
                defaultFullHttpRequest.headers().set("messageType", "normal");  
                defaultFullHttpRequest.headers().set("businessType", "testServerState");  
                // 发送http请求  
                future.channel().write(defaultFullHttpRequest);  
                future.channel().flush();  
                future.channel().closeFuture().sync();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            future.channel().writeAndFlush(request).sync();
//            synchronized (obj) {
//                obj.wait(); // 未收到响应，使线程等待
//            }
//            if (response != null) {
//                future.channel().closeFuture().sync();
//            }

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
