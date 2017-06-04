package mvc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import mvc.dao.HelloTpDao;

@Component
public class FacadeTpDao {

    @Resource
    protected HelloTpDao helloTpDao;

    public HelloTpDao getDemoTpDao() {
        return helloTpDao;
    }

    public void setDemoTpDao(HelloTpDao helloTpDao) {
        this.helloTpDao = helloTpDao;
    }

}
