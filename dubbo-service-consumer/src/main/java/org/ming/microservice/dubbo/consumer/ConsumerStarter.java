package org.ming.microservice.dubbo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.ming.dubbo.microservice.api.PersonService;
import com.ming.dubbo.microservice.common.util.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * @author MingXiangjun
 * @create 2018-07-01 14:41
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerStarter {
    private static KafkaConsumer consumer = getConsumer();

    public static void main(String[] args) {
        SpringApplication.run(ConsumerStarter.class,args);
    }

    public static KafkaConsumer getConsumer(){
        PropertyResourceBundle bundle = PropertiesUtil.getPropertiesResource("","kafka.properties");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bundle.getString("bootstrap.servers"));
        properties.put("enable.auto.commit", "true");
        properties.put("group.id", bundle.getString("group.id"));
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.commit.interval.ms", bundle.getString("auto.commit.interval.ms"));
        properties.put("key.deserializer", bundle.getString("key.deserializer"));
        properties.put("value.deserializer", bundle.getString("value.deserializer"));
        return new KafkaConsumer(properties);
    }
    private static void consume() {
        consumer.subscribe(Arrays.asList("kafka"));
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(2000);
            for (ConsumerRecord<String,String> record:records){
                System.out.println("===========>>>>>>>"+record.key()+"\t"+record.value());
            }
        }
    }
}
