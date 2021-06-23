package com.elliot.boot2.testboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数处理:
 * 1.路径变量获取 : @PathVariable: 可以接受一个一个,也可将路径上的参数封装到Map<String,String> map 上;
 * 2.获取请求头参数: @RequestHeader,可以封装到Map中
 * 3.获取请求参数: @RequestParam,可以封装到Map中
 * 4.获取cookie的值: @CookieValue
 * 5.获取请求体的值(POST请求): @RequestBody
 *       5.1 将form表单中的所有值,以(@RequestBody("content") String content) 来接收,
 * 6.获取请求域中的值: @RequestAttribute
 * 7.矩阵变量 @MatrixVariable    如路径格式: /car/{path};low=1;brand=fe,bmw,be
 *          springboot 默认禁用矩阵变量功能
 *          整个路径的处理,都是使用UrlPathHelper 进行解析
 *   如何自定义springMVC中的组件?
 *
 * @author: Elliot Ji
 * @createDate: 2021-06-23
 **/
@Controller
public class parameterTestController {

    @ResponseBody
    @GetMapping("/car/{id}/owner/{name}")
    public Map<String, Object> getUser(@PathVariable("id") Integer id,
                                       @PathVariable("name") String name,
                                       @PathVariable Map<String, String> pv,
                                       @RequestHeader("User-Agent") String userAgent,
                                       @RequestHeader Map<String,String> headers,
                                       @RequestParam("age") Integer age,
                                       @RequestParam("inters")List<String> inters,
                                       @RequestParam Map<String,String> params,
                                       @CookieValue("NTKF_T2D_CLIENTID")String _cookie,
                                       @CookieValue("NTKF_T2D_CLIENTID")Cookie cookie) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv",pv);
        map.put("User-Agent",userAgent);
        map.put("headers",headers);
        map.put("age",age);
        map.put("inters",inters);
        map.put("params",params);
        map.put("NTKF_T2D_CLIENTID",_cookie);
        System.out.println(cookie.getValue());
        return map;
    }

    @GetMapping("/goto")
    public String testParam(Map<String,Object> map,
                            Model model,
                            HttpServletRequest request){
        map.put("hello","hello666");
        model.addAttribute("world","world6666");
        request.setAttribute("helloworld","helloworld");
        return "forward:/back";
    }

    @GetMapping("/back")
    @ResponseBody
    public Map testBack(HttpServletRequest request){
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object helloworld = request.getAttribute("helloworld");
        Map<String,Object> map = new HashMap<>();
        map.put("hello",hello);
        map.put("world",world);
        map.put("helloworld",helloworld);
        return map;
    }





}
