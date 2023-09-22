package com.example.demo.test2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

//@Component
public class MeBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("初始化前====》" + "当前对象：" + bean + ",beanName = " + beanName);
            // 先实例化为User
            return new User();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("初始化后====》" + "当前对象：" + bean + ",beanName = " + beanName);
            // 再重新实例化为Person
            return new Person("mmy");
        }
        return bean;
    }
}

