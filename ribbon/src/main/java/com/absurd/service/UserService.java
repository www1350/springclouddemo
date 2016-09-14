package com.absurd.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/9/14.
 */
@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "userServiceFallback")
    public String getUser(Long id){
        return restTemplate.getForEntity("http://user-service/user/"+id, String.class).getBody();
    }

    public String userServiceFallback(Long id) {
        return "error";
    }

}
