package com.absurd.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2016/5/19.
 */
@FeignClient(value ="user-service", fallback = UserServiceHystrix.class)
public interface UserService {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    String getUser(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/user/getUser", method = RequestMethod.GET)
    String getUserByParam(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/login")
    String login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password);

}
