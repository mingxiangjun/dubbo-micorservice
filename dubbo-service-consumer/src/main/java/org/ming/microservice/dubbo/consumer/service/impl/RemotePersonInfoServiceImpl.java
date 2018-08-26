package org.ming.microservice.dubbo.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ming.dubbo.microservice.api.PersonService;
import lombok.extern.log4j.Log4j2;
import org.ming.microservice.dubbo.consumer.service.RemotePersonInfoService;
import org.springframework.stereotype.Service;

/**
 * 远程人员服务接口调用实现
 *
 * @author MingXiangjun
 * @create 2018-08-26 19:51
 */
@Log4j2
@Service
public class RemotePersonInfoServiceImpl implements RemotePersonInfoService {
    @Reference
    PersonService personService;
    @Override
    public String buildPersonInfo(String name) {
        log.info("<<<<<<<<buildPersonInfo(String)<<<<<<<<<<<<<<============");
        return personService.buildPersonInfo(name).toString();
    }
}
