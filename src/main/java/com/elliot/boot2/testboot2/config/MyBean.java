package com.elliot.boot2.testboot2.config;

import ch.qos.logback.core.db.DBHelper;
import com.elliot.boot2.testboot2.bean.Car;
import com.elliot.boot2.testboot2.bean.Pet;
import com.elliot.boot2.testboot2.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 向容器中注入对象的方式:
 * 方式一: @Configuration(配置类)和@Bean  就等同于 配置文件与<bean></bean>标签,默认是单实例的;
 *         proxyBeanMethods:代理bean方法. 默认是true,当前的配置类是通过cglib产生的代理对象, 确保都是单实例
 *                           改为false时,该配置类就不是通过cglib产生的代理对象,
 *                           false:代表lite轻量级
 *                           true: 代表full全量级
 *                           借此来解决组件依赖问题: 比如: User中有Pet属性,如果是true模式,user对象中的Pet依赖了容器中的
 *                           Pet对象,两者是同一个对象
 *                           设置成false,加载时就比较快
 *
 * 方式二: 常用的组件注解: @Controller, @Service等
 * 方式三: 利用@Import,用在容器的一个组件类上!
 *          给容器中自动创建这两个类的对象(调用无参构造方法),组件名默认是全类名
 *
 * 方式四: @Conditional ,满足指定条件时,注入组件
 * 方式五: @ImportResource, 用在容器的一个组件类上,以前的配置文件中的<bean></bean>,将这些实例如何快速导入容器中?
 *
 *在配置类上进行属性绑定
 *    1. @EnableConfigurationProperties(Car.class)
 *               开启Car属性绑定功能,然后自动将Car注册到容器中
 *               一般在引用第三方包中的对象时,可以如此使用
 *
 * @author: Elliot Ji
 * @createDate: 2021-06-22
 **/
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(value= ElasticConfig.class)
@ImportResource("classpath:bean.xml")
@EnableConfigurationProperties(Car.class)
public class MyBean {

    /**
     * 外部通过MyBean方法调用获取的对象,无论调用多少次,获取的都是从容器中产生的同一个对象
     * 方法名作为组件的id,返回值类型就是组件类型
     * @return
     */
    //@ConditionalOnBean(name="user01")
    @Bean("cat")
    public Pet myPet(){
        return new Pet();
    }

    @Bean
    public User user01(){
        return new User();
    }
}
