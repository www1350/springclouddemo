package com.absurd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Administrator on 2016/9/14.
 */
@EnableEurekaServer
@SpringBootApplication
public class EureKaApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EureKaApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EureKaApplication.class);
    }
}
