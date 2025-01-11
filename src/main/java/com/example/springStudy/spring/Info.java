package com.example.springStudy.spring;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
@ToString
public class Info implements BeanFactoryAware, BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

    private String telephone;

    private String address;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    private String beanName;

    public Info() {
        log.info("execute Info#new Info()");
    }

    public void setTelephone(String telephone) {
        log.info("execute Info#setTelephone({})", telephone);
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        log.info("execute Info#setAddress({})", address);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("execute BeanFactoryAware#setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        log.info("execute BeanNameAware#setBeanName");
        this.beanName = s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("execute ApplicationContextAware#setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        log.info("execute DisposableBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("execute InitializingBean#afterPropertiesSet");
    }


    public void doInit() {
        log.info("execute Info#doInit");
    }

    public void doDestroy() {
        log.info("execute Info#doDestroy");
    }

}