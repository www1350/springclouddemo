package com.absurd.controller;

import com.absurd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/9/14.
 */
@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${userid}")
    private Long userid;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(){
        return   userService.getUser(userid);
    }

    @RequestMapping(value = "/dev", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(){
        return   userService.login(username,password);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable("id") Long id){
      return   userService.getUser(id);
    }



}
