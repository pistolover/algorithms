package netty.http2;

import org.springframework.web.client.RestTemplate;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class HttpHandler extends SimpleChannelInboundHandler<Object> {
    
    
    private static final RestTemplate restTemplate = new RestTemplate();

    
    public void messageReceived(ChannelHandlerContext ctx, Object msg) {
        if(ctx == null) return;
        if (msg instanceof FullHttpRequest) {// 如果是HTTP请求，进行HTTP操作
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 如果是Websocket请求，则进行websocket操作
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    // 处理HTTP的代码
    private String handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        HttpMethod method = req.getMethod();
        String uri = req.getUri();
        return diapathcer(method, uri);
    }

    public static <T> T getForObject(String url, Class<T> responseType) throws Exception {
        try {
            T t = restTemplate.getForObject(url, responseType);
            return t;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    private String diapathcer(HttpMethod method, String uri) {
        String forObject= null;
        if (method == HttpMethod.GET ) {
            if(uri!=null && uri.startsWith("/tv-channel/programlist/get.json")){
               //调用第三方
                try {
                    String url = "http://d.itv.letv.com/live/speciallist/detail.json?salesArea=CN&countryArea=CN&terminalApplication=letv_superlive_app&versionCode=110&token=103XXXWLRdk5vWwOfDAcxGvaAB7zdhUnKm2QBo31r62KiyslylPuNsqJvhepFDm5nLBtDgM7d3BEVG4PL5vm2Y327TZSim3xwSiAKYx5RnUwL0m1yV4m26km4&mac=&listid=todayprograms&bsChannel=letv_live&devKey=&appVersion=3.5.0.010&terminalBrand=letv&cityInfo=&uid=130507449&terminalSeries=LEX720_whole-netcom&pcode=160110000&langcode=zh_CN&devId=861579030027248";
                    forObject = getForObject(url, String.class);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // ....处理
        } else if (method == HttpMethod.POST && "/register".equals(uri)) {
            // ...处理
        }
        return forObject;
    }

    // 处理Websocket的代码
    private String handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String object = null;
        if (msg instanceof FullHttpRequest) {// 如果是HTTP请求，进行HTTP操作
            object = handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 如果是Websocket请求，则进行websocket操作
            object = handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }

        if(object!=null){
            ByteBuf buffer= Unpooled.buffer(1024);
            buffer.writeBytes(object.getBytes());
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
            response.headers().set("Content-Type", "text/html; charset=UTF-8");
            response.headers().set("Content-Length", response.content().writerIndex());
            Channel ch = ctx.pipeline().channel();
            // Write the initial line and the header.
            ch.writeAndFlush(response);
            ch.disconnect();
            ch.close();
        }
    }
}