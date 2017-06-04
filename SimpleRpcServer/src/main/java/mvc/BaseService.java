package mvc;

import javax.annotation.Resource;

public class BaseService {

    @Resource
    protected FacadeService FacadeService;

    @Resource
    protected FacadeTpDao facadeTpDao;

}
