package com.ming.dubbo.microservice.common.util;

import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.PropertyResourceBundle;

/**
 * properties文件工具类
 * @author mingxiangjun
 * @date 2018/7/11 上午10:46
 */

public class PropertiesUtil {
    private static final String defaultConfigurationFileName = "commonconfig.properties";
    private static PropertyResourceBundle properties = null;

    public static PropertyResourceBundle getPropertiesResource(String filePath,String fileName){
        PropertyResourceBundle bundle = null;
        fileName = StringUtils.isEmpty(filePath)?fileName:(filePath+File.separator+fileName);
        try {
            bundle = new PropertyResourceBundle(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
//            log.info("get default configuration file error , the file is :{}",fileName);
            e.printStackTrace();
        }
        return bundle;
    }

    public static String getString(String path,String fileName,@NotNull String key){
        if (StringUtils.isEmpty(fileName)){
            properties = getPropertiesResource("",defaultConfigurationFileName);
        }else{
            properties = getPropertiesResource(path,fileName);
        }
        return properties.getString(key);
    }

    public static Object getObject(String path,String fileName,@NotNull String key){
        if (StringUtils.isEmpty(fileName)){
            properties = getPropertiesResource("",defaultConfigurationFileName);
        }else{
            properties = getPropertiesResource(path,fileName);
        }
        return properties.getObject(key);
    }
}
