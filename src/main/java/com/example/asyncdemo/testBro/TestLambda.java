package com.example.asyncdemo.testBro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestLambda {
    public static void main(String[] args) {
        Object invocation = new CglibProxy().getProxy(new SayWelcome());
        SayWelcome invocation1 = (SayWelcome) invocation;
        invocation1.welcome(new Teacher("jack",23));
    }
}

@AllArgsConstructor
@Getter
@Setter
class Teacher{
    String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    Integer age;
}

class SayWelcome {
    public void welcome (Teacher teacher){
        System.out.println("welcome :" + teacher + Thread.currentThread().getName());
    }
}

class CglibProxy implements MethodInterceptor {
    private Object target;
    public Object getProxy(Object source){
        target = source;
        Enhancer enhancer = new Enhancer();
        // 获取被代理类
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调函数，感觉也可以称为设置方法拦截
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before method!!!啊啊");
        Callable<Object> task = () -> {
            Object result = null;
            try {
                result = proxy.invokeSuper(obj,args);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        return result;
        };
        FutureTask<Object> objectFutureTask = new FutureTask<>(task);
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.execute(objectFutureTask);
        System.out.println(simpleAsyncTaskExecutor.getThreadNamePrefix());
        return null;
    }
}


