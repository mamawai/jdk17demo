package com.example.demo.test2;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Client {
    public static void main(String[] args) {
//        ClassPathResource resource = new ClassPathResource("spring-config.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        // 加载xml文件
//        reader.loadBeanDefinitions(resource);
//
//        MeBeanPostProcessor meBeanPostProcessor = new MeBeanPostProcessor();
//        factory.addBeanPostProcessor(meBeanPostProcessor);
//
//        Object person = factory.getBean("person");
//        Object user = factory.getBean("user");
//
//        System.out.println(person);
//        System.out.println(user);

        // 另一种写法
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        Object person = applicationContext.getBean("person");
        Object user = applicationContext.getBean("user");
        System.out.println(person);
        System.out.println(user);
    }
}
