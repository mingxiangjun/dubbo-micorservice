package com.ming.dubbo.microservice.dubbo.provider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author MingXiangjun
 * @create 2018-07-01 14:40
 */
@SpringBootApplication
public class ProviderStarter {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ProviderStarter.class,args);
    }

    private static void providerStarter() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        System.out.println(context.getDisplayName() + ": here");
        context.start();
        System.out.println("服务已经启动...");
        System.in.read();
    }

}
