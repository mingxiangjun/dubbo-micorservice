package com.ming.dubbo.microservice.dubbo.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.ming.dubbo.microservice.api.PersonService;
import com.ming.dubbo.microservice.common.util.PropertiesUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.Random;

/**
 * @author MingXiangjun
 * @create 2018-07-01 13:25
 */
@Log4j2
@Service(interfaceClass = PersonService.class ,timeout =800000)
@Component
public class PersonServiceImpl implements PersonService {
    private static String topic = "kafka";
    @Override
    public JSONObject buildPersonInfo(String userName) {
        log.info("==============>>>>>>>>>>buildPersonInfo<<<<<<<<<<<<<<============");
        Random random = new Random();
        JSONObject resultJson = new JSONObject();
        resultJson.put("userName", userName+random.nextInt(300));
        resultJson.put("age","age"+random.nextInt(200));
        log.info("==============>>>>>>>>>>MDC info<<<<<<<<<<<<<<============");
//        KafkaProducer producer = getProducer();
//        producer.send(new ProducerRecord(topic,"key",resultJson.toJSONString()));
//        producer.close();
        return resultJson;
    }
    private static KafkaProducer getProducer(){
        PropertyResourceBundle propertiesResource = PropertiesUtil.getPropertiesResource("", "kafka.properties");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", propertiesResource.getString("bootstrap.servers"));
        properties.put("request.required.acks", propertiesResource.getString("request.required.acks"));
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", propertiesResource.getString("key.serializer"));
        properties.put("value.serializer", propertiesResource.getString("value.serializer"));
        return new KafkaProducer(properties);
    }
}
