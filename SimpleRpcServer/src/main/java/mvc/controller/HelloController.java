package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mvc.BaseController;
import mvc.service.HelloService;
@Controller
public class HelloController extends BaseController{
    
    @RequestMapping(value = "/demo/get")
    public String get(@RequestParam(value = "id") String id) {
        return this.facadeService.getDemoService().get();
    }
}
