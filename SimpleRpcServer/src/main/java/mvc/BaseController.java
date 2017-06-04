package mvc;

import javax.annotation.Resource;

public class BaseController {

    @Resource
    protected FacadeService facadeService;
}
