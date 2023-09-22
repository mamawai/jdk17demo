package com.example.demo.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.List;


public class TestRegisterResolvableDependency implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        beanFactory.registerResolvableDependency(ComponentMain.class, new ComponentMainBImpl());
        beanFactory.ignoreDependencyInterface(IgnoreInterface.class);
    }
}


