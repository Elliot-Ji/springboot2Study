package com.elliot.boot2.testboot2.controller;

import com.elliot.boot2.testboot2.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 请求映射原理:
 *     1.Rest风格的请求映射处理, 即路径都是/user, 以请求方式作为区别,method=RequestMethod.GET 等
 *          1.1 页面以<form></form>表单的方式发送  核心是 HiddenHttpMethodFilter,
 *          且在配置文件中开启这个功能,默认是不开启的,
 *          1.2 如果不是表单方式发送的,直接通过postman等客户端工具发送请求,是不通过那个判断的filter的,
 *     2.核心: 所有请求都会通过DisPatcherServlet中的doService()-->doDispatch()方法
 *         2.1 每一个请求进来,根据路径等信息,从中找到对应的HandlerMapping(即Controller)
 *               也可自定义HandlerMapping
 *
 * 请求处理:
 *    doDispatcherServlet
 *    (1).标注了注解的参数处理和servlet api 中的参数处理
 *       1.找到对应的mappedHandler映射处理器, HandlerMapping(Controller),
 *       2.找到对应的HandlerAdapter适配器,在该适配器中存在参数解析器,用于解析参数;返回值处理器
 *         2.1 核心: 如何确定方法参数的值;
 *           2.1.1 循环判断哪个参数解析器可以解析目标方法上的参数(每一个参数都会循环判断,找到对应的参数解析器),找到之后会放入缓存中
 *           2.1.2 找到参数解析器之后,对目标参数进行解析:  获取路径参数名和路径参数值
 *    (2).复杂参数处理: 如 Map,Model(这两个对象默认都会将值放入到请求域中),HttpServletRequest...
 *           Map和Model在参数解析器中最后都封装到通一个对象中 BindingAwareModelMap
 *           源码中在处理视图解析的过程中,会将modelMap中的数据直接放入到请求域中,即调用request.setAttribute(name,value);
 *    (3).自定义对象参数处理:
 *          页面中传来的值是如何与自定义对象中的属性一一绑定的?
 *          1. 自定义参数的解析器是: ServletModelAttributeMethodProcessor
 *          2. 利用 WebDataBinder  web数据绑定器 里面有文本转换器,即将http中传过来的数据(万物皆文本)转换成对应的数据类型
 *          3.@RequestBody application/json，application/xml 解析json等格式的请求体数据
 *            springMVC只能解析原生表单application/x-www-form-urlencoded,所以不需要加@RequestBody
 *            文件上传数据 @RequestBody也不能解析,加上注解也没用;
 */
@Controller
public class ViewTestController {

    @Autowired
    Car car;

    /**
     * 要注意: @Controller 与@RestController的区别
     *        一个返回页面,一个返回json字符串
     * @param model
     * @return
     */
    @GetMapping("/atguigu")
    public String atguigu(Model model) {
        //model中的数据会被放入请求域中，等价于request.setAttribute("a","ad")
        model.addAttribute("msg","你好，H哈");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }

    @RequestMapping(value = "/car")
    public Car getCar(){
        return car;
    }

    @GetMapping("/user")
    public String getUser(){
        return  "GET user";
    }

    @PostMapping("/user")
    public String postUser(){
        return  "POST user";
    }

    @DeleteMapping("/user")
    public String deleteUser(){
        return  "DELETE user";
    }

    @PutMapping("/user")
    public String putUser(){
        return  "PUT user";
    }

}
