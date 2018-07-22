package com.ming.dubbo.microservice.dubbo.provider;


import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author MingXiangjun
 * @create 2018-07-01 14:40
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ProviderStarter {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ProviderStarter.class,args);
    }
}
