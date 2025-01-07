package com.example.demo.springAop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("aspects.xml");

        // retrieve configured instance
        UserServiceImpl service = context.getBean("demoService", UserServiceImpl.class);

        // use configured instance
        List<User> userList = service.findUserList();
        service.addUser();
    }
}
