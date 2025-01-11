package com.example.springStudy.spring;

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
                "com.example.springStudy.spring");

        // retrieve configured instance
        User user = (User) context.getBean("user");

        /*
          （Lazy Loading）在一个bean A的依赖里面如果有某个bean B上面加上@Lazy注解，
          那么在bean A进行实例化依赖注入初始化过程中，bean B是不会实例化的，
          只有当A.B被调用的时候比如toString getB里面的值,这时候bean B才会被实例化依赖注入以及初始化。
         */
        /*
         * populateBean 方法后执行的，也就是说在user Bean依赖注入的时候 为info设置了一个代理对象
         * 在自动注入的时候 这个方法判断了 field上面有没有Lazy注解 如果有构建代理对象
        public Object getLazyResolutionProxyIfNecessary(DependencyDescriptor descriptor, @Nullable String beanName) {
		return (isLazy(descriptor) ? buildLazyResolutionProxy(descriptor, beanName) : null);
	}
         *
         */


        String name = user.getInfo().getClass().getName();
        log.info(name); // cglib代理
        log.info("getInfo");
        log.info(user.getInfo().getAddress()); // 只有真正调用到了info对象才会初始化
        log.info("before toString");
        // print info from beans
        log.info(user.toString());
        log.info("Shutdown application context");
        context.registerShutdownHook();
    }
}
