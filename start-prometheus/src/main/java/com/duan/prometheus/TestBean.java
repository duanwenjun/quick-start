package com.duan.prometheus;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author duanwenjun
 */
public class TestBean {

    //@Bean(name = "testName")
    @PostConstruct
    void test() {
        System.out.println("初始化执行");
    }


}
