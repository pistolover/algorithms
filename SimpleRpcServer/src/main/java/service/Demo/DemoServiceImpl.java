package service.Demo;

import service.common.RpcServiceAnno;
import service.demo.DemoService;

@RpcServiceAnno(value = "demoService", inf = DemoService.class)
public class DemoServiceImpl implements DemoService{

    @Override
    public String getDemo() {
       return "I am test rpc ......";
        
    }

}
