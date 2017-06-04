package mvc;

import javax.annotation.Resource;

import org.springframework.web.client.RestTemplate;

public class BaseTpDao {
    @Resource
    private RestTemplate restTemplate;
    
}
