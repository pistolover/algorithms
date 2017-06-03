package rpc.netty.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rpc.netty.server.RpcProxy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:client.xml")
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Autowired
    private RpcProxy rpcProxy;
    
    @Test
    public void test(){
        DemoService demoService = rpcProxy.Create(DemoService.class);
        String demo = demoService.getDemo();
        
        
        logger.info(demo);
        logger.trace(demo);
        System.err.println("demo result: " + demo);
    }
    
}
