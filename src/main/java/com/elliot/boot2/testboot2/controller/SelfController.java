package com.elliot.boot2.testboot2.controller;

import com.elliot.boot2.testboot2.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Elliot Ji
 * @createDate: 2021-06-23
 **/
@RestController
public class SelfController {

    @PostMapping("/users")
    public User getUser(@RequestBody  User user){
        return user;
    }

}
