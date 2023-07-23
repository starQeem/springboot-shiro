package com.starQeem;

import com.starQeem.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Resource
    private userService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.list());
    }

}
