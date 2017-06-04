package mvc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import mvc.service.HelloService;

@Component
public class FacadeService {
    @Resource
    private HelloService helloService;

    public HelloService getDemoService() {
        return helloService;
    }

    public void setDemoService(HelloService helloService) {
        this.helloService = helloService;
    }

}
