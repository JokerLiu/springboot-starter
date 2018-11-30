package com.example.springbootcachestarter;

import com.joker.cache.bean.Employee;
import com.joker.cache.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheStarterApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void TestStringRedisTemplate() {
        // Integer append = stringRedisTemplate.opsForValue().append("msg", "world");
        // log.info("append:{}", append);
        // String msg = stringRedisTemplate.opsForValue().get("msg");
        // log.info("msg:{}", msg);
        // Long myList = stringRedisTemplate.opsForList().leftPush("myList", "1");
        // log.info("myList:{}", myList);
        // myList = stringRedisTemplate.opsForList().leftPush("myList", "2");
        // log.info("myList:{}", myList);

        String myList = stringRedisTemplate.opsForList().leftPop("myList");
        log.info("myList:{}", myList);
    }

    @Test
    public void TestRedisTemplate() {

    }

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        log.info("emp:{}", employee);
        Integer insertEmp = employeeMapper.insertEmp(new Employee(2, "李四", "123@qq.com", 1));
        log.info("insertEmp:{}", insertEmp);
        Integer updateEmp = employeeMapper.updateEmp(new Employee(1, "张三"));
        log.info("updateEmp:{}", updateEmp);
    }

}
