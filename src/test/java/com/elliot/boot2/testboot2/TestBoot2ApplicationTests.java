package com.elliot.boot2.testboot2;

import com.alibaba.fastjson.JSON;
import com.elliot.boot2.testboot2.config.ElasticConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class TestBoot2ApplicationTests {

    @Resource
    private RestHighLevelClient client;


    @Test
    public void addDocument() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        //indexRequest.source("username","lilly","age",18,"gender","男");
        User user = new User();
        user.setUserName("liily");
        user.setGender("女");
        user.setAge(18);
        String s = JSON.toJSONString(user);
        indexRequest.source(s, XContentType.JSON);

        IndexResponse index = client.index(indexRequest, ElasticConfig.COMMON_OPTIONS);
        System.out.println(index);
    }


    @Test
    public void contextLoads() {
        System.out.println(client);
    }

     @Data
    class User{
        private String userName;
        private String gender;
        private Integer age;
    }

}
