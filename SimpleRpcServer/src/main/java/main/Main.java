package main;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utils.LogUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Test
    public void demoTest() throws InterruptedException {
        LogUtils.info(logger, "启动...");
        TimeUnit.HOURS.sleep(5);
    }
}
