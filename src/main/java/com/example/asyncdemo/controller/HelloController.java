package com.example.asyncdemo.controller;

import com.example.asyncdemo.async.MyService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {

    private final MyService myService;
//    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/hello")
    public String hello(){
//        System.out.println(threadPoolTaskExecutor.getThreadNamePrefix());
        myService.performAsyncTasks();
        return "hello world";
    }
}
