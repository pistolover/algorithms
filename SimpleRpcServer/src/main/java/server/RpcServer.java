package server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import request.Request;
import response.Response;
import serialize.RpcByteToMessageDecode;
import serialize.RpcMessageToByteEncode;
import service.common.RpcServiceAnno;
import service.handle.RpcHandler;
import service.register.RpcRegister;
import utils.LogUtils;

public class RpcServer implements ApplicationContextAware, InitializingBean {
    private String serverAddress;
    private RpcRegister rpcRegister;
    private Map<String, Object> handlerMap = new ConcurrentHashMap<String, Object>();
    private static final Logger logger     = LogManager.getLogger(RpcServer.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RpcByteToMessageDecode(Request.class))
                    .addLast(new RpcMessageToByteEncode(Response.class))
                    .addLast(new RpcHandler(handlerMap));
                }
                
            }).option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);
            
            
            String[] split = serverAddress.split(":");
            ChannelFuture channelFuture = bootstrap.bind(split[0], Integer.parseInt(split[1])).sync();
            if(rpcRegister != null) {
                rpcRegister.register(serverAddress); // 注册服务地址

            }
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        Map<String, Object> serviceMap = context.getBeansWithAnnotation(RpcServiceAnno.class);
        LogUtils.info(logger, "获取到所有的RPC服务有：{0}", serviceMap);
        if(!CollectionUtils.isEmpty(serviceMap)) {
            for (Object value : serviceMap.values()) {
                String interName = value.getClass().getAnnotation(RpcServiceAnno.class).inf().getName();
                handlerMap.put(interName, value);
            }
        }
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcRegister getRpcRegister() {
        return rpcRegister;
    }

    public void setRpcRegister(RpcRegister rpcRegister) {
        this.rpcRegister = rpcRegister;
    }

    public Map<String, Object> getHandlerMap() {
        return handlerMap;
    }

    public void setHandlerMap(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

}
