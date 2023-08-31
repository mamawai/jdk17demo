package com.example.asyncdemo.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final MyAsyncTasks asyncTasks;

    @Autowired
    public MyService(MyAsyncTasks asyncTasks) {
        this.asyncTasks = asyncTasks;
    }

    public void performAsyncTasks(){
        asyncTasks.doTask1();
        asyncTasks.doTask2();
        System.out.println(Thread.currentThread().getName()+"执行");// http-nio-8080-exec-1
    }

}
