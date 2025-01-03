package com.example.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class App {

    /**
     * main interface.
     *
     * @param args args
     */
    public static void main(String[] args) {
        log.info("Init application context");
        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.example.demo.spring");

        // retrieve configured instance
        User user = (User) context.getBean("user");

        /*
          （Lazy Loading）在一个bean A的依赖里面如果有某个bean B上面加上@Lazy注解，
          那么在bean A进行实例化依赖注入初始化过程中，bean B是不会实例化的，
          只有当A.B被调用的时候比如toString getB里面的值,这时候bean B才会被实例化依赖注入以及初始化。
         */

        // print info from beans
        log.info(user.toString());

        log.info("Shutdown application context");
        context.registerShutdownHook();
    }
}
