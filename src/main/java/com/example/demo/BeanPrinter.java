package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BeanPrinter {

    private final ApplicationContext applicationContext;

    public void printBeans(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:beanDefinitionNames){
            if ("authenticationManagerBuilder".equals(beanName)){
                System.out.println(beanName);
            }
            Object bean = applicationContext.getBean(beanName);
            System.out.println("Bean Name: "+beanName+", Bean Class: "+bean.getClass().getName());
        }
    }
}
