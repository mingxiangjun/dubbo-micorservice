package com.ming.dubbo.microservice.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.ming.dubbo.microservice.api.PersonService;

import java.util.Random;

/**
 * @author MingXiangjun
 * @create 2018-07-01 13:25
 */
public class PersonServiceImpl implements PersonService {
    public JSONObject buildPersonInfo() {
        JSONObject resultJson = new JSONObject();
        resultJson.put("userName", "name");
        resultJson.put("age","age");
        return resultJson;
    }
}
