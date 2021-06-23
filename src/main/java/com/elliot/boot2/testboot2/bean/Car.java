package com.elliot.boot2.testboot2.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性配置: 配置文件中的值与Javabean对象绑定
 * 方式一: 普通的bean对象上标注 @Component+@ConfigurationProperties(prefix = "mycar")
 * 方式二: 在配置类上加注解
 * @author: Elliot Ji
 * @createDate: 2021-06-22
 **/
@Data
//@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String band;
    private Integer price;
}
