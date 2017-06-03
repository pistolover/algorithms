package rpc.netty.test;

import rpc.netty.server.RpcServiceAnno;

@RpcServiceAnno(value = "demoService", inf = DemoService.class)
public class DemoServiceImpl implements DemoService{

    @Override
    public String getDemo() {
       return "I am test rpc ......";
        
    }

}
