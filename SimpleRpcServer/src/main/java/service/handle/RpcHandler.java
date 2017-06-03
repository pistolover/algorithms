package service.handle;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import request.Request;
import response.Response;

public class RpcHandler extends SimpleChannelInboundHandler<Request>{

    private Map<String, Object> handlers;
    
    public RpcHandler(Map<String, Object> handlerMap) {
        // TODO Auto-generated constructor stub
        this.handlers = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        // TODO Auto-generated method stub
        Response response = new Response();
        response.setRequestId(msg.getRequestId());
        
        
        try {
            Object result = this.handler(msg);
            response.setResult(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        
        
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
