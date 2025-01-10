package com.example.demo.springAop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context = new ClassPathXmlApplicationContext("aspects.xml");

        // retrieve configured instance
        // UserServiceImpl service = context.getBean("demoService", UserServiceImpl.class); // cglib
        UserService service = context.getBean("demoService", UserService.class); // jdk

        // use configured instance
        service.findUserList();
        service.addUser();
    }
}
