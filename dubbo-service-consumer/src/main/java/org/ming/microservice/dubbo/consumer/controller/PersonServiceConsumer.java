package org.ming.microservice.dubbo.consumer.controller;

import lombok.extern.log4j.Log4j2;
import org.ming.microservice.dubbo.consumer.service.RemotePersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MingXiangjun
 * @create 2018-07-21 15:22
 */
@Log4j2
@RestController
@RequestMapping(value = "/person")
public class PersonServiceConsumer {
    @Autowired
    RemotePersonInfoService personInfoService;

    @RequestMapping(value = "/getPersonInfo")
    public String buildPersonInfo(@RequestParam(value = "name")String userName){
        log.info("==============>>>>>>>>>>getPersonInfo<<<<<<<<<<<<<<============");
        return personInfoService.buildPersonInfo(userName);
    }
}
