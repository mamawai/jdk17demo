package com.example.demo;

import com.example.demo.test.IgnoreInterfaceImpl;
import com.example.demo.test.TestRegisterResolvableDependency;
import com.example.demo.test2.MeBeanPostProcessor;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestSpring {

//    @Autowired
//    ComponentMain componentMain;
//
//    @Test
//    public void test(){
//        ComponentMainBImpl componentMainB = (ComponentMainBImpl) componentMain;
//        String mark = componentMainB.getMark();
//        System.out.println(mark);
//    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        Object person = applicationContext.getBean("person");
        Object user = applicationContext.getBean("user");
        System.out.println(person);
        System.out.println(user);
    }

}

