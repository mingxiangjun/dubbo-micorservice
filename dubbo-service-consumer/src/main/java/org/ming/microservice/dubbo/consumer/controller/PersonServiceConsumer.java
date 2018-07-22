package org.ming.microservice.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ming.dubbo.microservice.api.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MingXiangjun
 * @create 2018-07-21 15:22
 */
@RestController
@RequestMapping(value = "/person")
public class PersonServiceConsumer {
    @Reference
    PersonService personService;

    @RequestMapping(value = "/getPersonInfo")
    public String buildPersonInfo(){
        return personService.buildPersonInfo().toString();
    }
}
