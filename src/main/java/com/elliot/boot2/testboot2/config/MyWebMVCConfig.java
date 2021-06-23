package com.elliot.boot2.testboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * 自定义springMVC中的组件
 * 方式一: implements WebMVCConfigurer,重新其中的方法;
 * 方式二: 利用@Bean注解,注入WebMvcConfigurer对象,重新其中的方法;
 * @author: Elliot Ji
 * @createDate: 2021-06-23
 **/
@Configuration(proxyBeanMethods = false)
public class MyWebMVCConfig /*implements WebMvcConfigurer*/ {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
         return new WebMvcConfigurer(){
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);//开启矩阵变量功能
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }

   /* @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(true);//开启矩阵变量功能
        configurer.setUrlPathHelper(urlPathHelper);
    }*/
}
