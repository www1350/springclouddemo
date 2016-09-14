package com.absurd.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Administrator on 2016/9/14.
 */
@Component
public class UserServiceHystrix implements  UserService{
    @Override
    public String getUser(@PathVariable(value = "id") Long id) {
        return "error";
    }
}
