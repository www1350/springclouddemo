package com.absurd.controller;

import com.absurd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/9/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable("id") Long id){
       return  userService.getUser(id);
    }
}
