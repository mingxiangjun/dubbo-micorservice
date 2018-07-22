package com.ming.dubbo.microservice.api;

/**
 * 消息队列代理
 *
 * @author MingXiangjun
 * @create 2018-07-22 21:45
 */
public interface MQDelegate<T,V> {
    void sendMessage(String topic ,T key, V value);
    void consumerMessage();
}
