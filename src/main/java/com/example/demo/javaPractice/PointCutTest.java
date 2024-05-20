package com.example.demo.javaPractice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class PointCutTest {
    @Pointcut("execution(public * com.example.demo.javaPractice.Divide.*(..))")
    public void pointcut(){

    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void logThrowing(JoinPoint joinPoint, Throwable ex) {
        log.info("***这里是切面异常处理***");
        log.info("请求类方法" + joinPoint.getSignature().getName());
        log.info("异常内容", ex);
        log.info("yichang" + ex);
    }
}
