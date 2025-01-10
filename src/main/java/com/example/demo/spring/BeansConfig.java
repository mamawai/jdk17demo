package com.example.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


//@Configuration
public class BeansConfig {


    @Bean(name = "user", initMethod = "doInit", destroyMethod = "doDestroy")
    public User create() {
        User user = new User();
        user.setName("mmy");
        user.setAge(18);
        return user;
    }

    @Lazy
    @Bean(name = "info", initMethod = "doInit", destroyMethod = "doDestroy")
    public Info createInfo() {
        Info info = new Info();
        info.setTelephone("123456");
        info.setAddress("road");
        return info;
    }
}