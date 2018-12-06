package com.joker.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/12/6 15:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AsyncTaskServiceTest {

    @Autowired
    AsyncTaskService asyncTaskService;

    @Test
    public void test() throws Exception {
        asyncTaskService.doTaskOne();
        asyncTaskService.doTaskTwo();
        asyncTaskService.doTaskThree();

        Thread.currentThread().join();// 阻塞当前主线程
    }

}