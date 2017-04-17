package com.em248.controller;

import com.em248.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tian on 2017/4/17.
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {


    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public void createUser(){

    }
}
