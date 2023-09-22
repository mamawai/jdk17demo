package com.example.demo.proxyJDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        // 创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");
        // 创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StuInvocationHandler<Person>(zhangsan);
        // 创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        // stuProxy的类不是Person而是java生成的代理类 com.sun.proxy.$Proxy0（可能有变化，我这里打印出是这个）Proxy0 implement Person
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);

        // 代理类叠加(匿名内部类lambda表达式写法)
        Person doubleProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, (proxy, method, args1) -> {
            System.out.println("流程开始");
            Object invoke = method.invoke(stuProxy, args1);
            System.out.println("流程结束");
            return invoke;
        });
        // 代理执行上交班费的方法
        stuProxy.giveMoney(50);
        doubleProxy.giveMoney(60);
    }
}
