package com.elliot.boot2.testboot2;

import com.elliot.boot2.testboot2.bean.Pet;
import com.elliot.boot2.testboot2.bean.User;
import com.elliot.boot2.testboot2.config.MyBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestBoot2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestBoot2Application.class, args);

        //查看容器中的所有组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        //获取某一组件
//        Object bean = run.getBean("cat");
//        System.out.println(bean);

//        MyBean bean1 = run.getBean(MyBean.class);
//        Pet pet = bean1.myPet();
//        Pet pet1 = bean1.myPet();
//        System.out.println(pet == pet1);
        User haha = run.getBean("haha", User.class);
        System.out.println(haha);


    }

}
