package rpc.netty.server;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import rpc.netty.client.RpcClient;
import rpc.netty.client.RpcDiscovery;
import rpc.netty.lib.response.Response;
import rpc.netty.lib.resquest.Request;

public class RpcProxy {
    private String serverAddress;
    private RpcDiscovery rpcDiscovery;

    @SuppressWarnings("unchecked")
    public <T> T Create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Request request = new Request();
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameters(args);
                        request.setParameterTypes(method.getParameterTypes());
                        if (rpcDiscovery != null) {
                            serverAddress = rpcDiscovery.descoveryService();
                        }
                        String[] split = serverAddress.split(":");

                        String host = split[0];
                        int port = Integer.parseInt(split[1]);

                        RpcClient client = new RpcClient(host, port);
                        Response send = client.send(request);
                        if (send.getError() != null) {
                            throw send.getError();
                        } else {
                            System.err.println("---------");
                            
                            return send.getResult();
                        }

                    }
                });
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcDiscovery getRpcDiscovery() {
        return rpcDiscovery;
    }

    public void setRpcDiscovery(RpcDiscovery rpcDiscovery) {
        this.rpcDiscovery = rpcDiscovery;
    }

}
