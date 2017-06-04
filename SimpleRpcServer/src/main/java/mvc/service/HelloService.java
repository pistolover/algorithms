package mvc.service;

import org.springframework.stereotype.Service;

import mvc.BaseService;

@Service
public class HelloService extends BaseService{

    public String get(){
        return this.facadeTpDao.getDemoTpDao().get();
    }
}
