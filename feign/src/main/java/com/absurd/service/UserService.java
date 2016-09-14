package com.absurd.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/5/19.
 */
@FeignClient(value ="user-service", fallback = UserServiceHystrix.class)
public interface UserService {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    String getUser(@PathVariable(value = "id") Long id);

}
