package com.elliot.boot2.testboot2.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Elliot Ji
 * @createDate: 2021-06-21
 **/
@Configuration
public class ElasticConfig {

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        //builder.addHeader("Authorization","Bearer"+"Token");
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient esClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.23.148", 9200, "http")));
        return client;
    }

}
