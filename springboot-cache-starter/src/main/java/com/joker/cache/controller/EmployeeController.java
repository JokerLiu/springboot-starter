package com.joker.cache.controller;

import com.joker.cache.bean.Employee;
import com.joker.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/11/30 16:17
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    // @Autowired
    // private RedisTemplate redisTemplate;

    /**
     * Cacheable核心：内部维护了一个ConcurrentMap[默认为：ConcurrentMapCacheManager类]
     * 在运行方法之前先去查询Cache（缓存组件），调用ConcurrentMapCacheManager.getCache(cacheNames)；
     * 如果获取结果为空，作为键存入ConcurrentMap<String, Cache>,不为空直接返回获取的结果。
     * Key的生成是通过SimpleKeyGenerator来生成
     * @param:
     * @return:
     * @author: Joker
     * @date: 2018/11/30 17:28
     */
    @Cacheable(cacheNames = "emp")
    @GetMapping("getEmpById/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id) {
        return employeeMapper.getEmpById(id);
    }

}