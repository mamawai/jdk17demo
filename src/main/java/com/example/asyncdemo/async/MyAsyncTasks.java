package com.example.asyncdemo.async;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyAsyncTasks {

    @Async
    public void doTask1(){
        // 异步任务1实现
        System.out.println("Task 1:Start:"+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 1：End");
    }

    @Async
    public void doTask2(){
        // 异步任务1实现
        System.out.println("Task 2:Start:"+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task 2：End");
    }


}

