package service.handle;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import request.Request;

public class RpcHandler extends SimpleChannelInboundHandler<Request>{
    private static Logger   logger  = LoggerFactory.getLogger(RpcHandler.class);  

    private Map<String, Object> handlers;
    
    private ByteBufToBytes reader;  

    public RpcHandler(Map<String, Object> handlerMap) {
        // TODO Auto-generated constructor stub
        this.handlers = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        
        
        if (msg instanceof HttpRequest) {  
            HttpRequest request = (HttpRequest) msg;  
            System.out.println("messageType:" + request.headers().get("messageType"));  
            System.out.println("businessType:" + request.headers().get("businessType"));  
            if (HttpHeaders.isContentLengthSet(request)) {  
                reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(request));  
            }  
        }  
  
        if (msg instanceof HttpContent) {  
            HttpContent httpContent = (HttpContent) msg;  
            ByteBuf content = httpContent.content();  
            reader.reading(content);  
            content.release();  
  
            if (reader.isEnd()) {  
                String resultStr = new String(reader.readFull());  
                System.out.println("Client said:" + resultStr);  
  
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("I am ok"  
                        .getBytes()));  
                response.headers().set("CONTENT_TYPE", "text/plain");  
                response.headers().set("CONTENT_LENGTH", response.content().readableBytes());  
                response.headers().set("CONNECTION", Values.KEEP_ALIVE);  
                ctx.write(response);  
                ctx.flush();  
            }  
        }  
        
        // TODO Auto-generated method stub
//        Response response = new Response();
//        response.setRequestId(msg.getRequestId());
//        
//        
//        try {
//            Object result = this.handler(msg);
//            response.setResult(result);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        
        
    }
    
    
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        logger.info("HttpServerInboundHandler.channelReadComplete");  
        ctx.flush();  
    }  

    private Object handler(Request msg) throws InvocationTargetException {
        String className = msg.getClassName();
        Object sericeBean = handlers.get(className);
        Class<?> serviceClass = sericeBean.getClass();
        String methodName = msg.getMethodName();
        Class<?>[] parameterTypes = msg.getParameterTypes();
        Object[] paramters = msg.getParameters();
        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod method = serviceFastClass.getMethod(methodName, parameterTypes);
        return method.invoke(sericeBean, paramters);
    }

}
