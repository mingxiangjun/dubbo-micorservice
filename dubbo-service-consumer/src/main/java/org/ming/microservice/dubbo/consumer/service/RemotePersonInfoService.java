package org.ming.microservice.dubbo.consumer.service;

/**
 * 远程人员信息服务接口调用
 *
 * @author MingXiangjun
 * @create 2018-08-26 19:50
 **/
public interface RemotePersonInfoService {
    String buildPersonInfo(String name);
}
