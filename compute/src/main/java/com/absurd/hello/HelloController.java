package com.absurd.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Administrator on 2016/9/19.
 */
@ApiIgnore
@RestController
public class HelloController {
    @RequestMapping("/")
    @ResponseBody
    String hello() {
        return "Hello World!";
    }
}
