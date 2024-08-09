package com.example.demo.algorithm;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class ThreadLocalTest {
    public static void main(String[] args) {
        new Thread(
                () -> {
                    ThreadLocalDemo.setLocalVar(new Command("jack", 18));

                    System.out.println(ThreadLocalDemo.localVar);
                    System.out.println(ThreadLocalDemo.getLocalVar());
                    ThreadLocalDemo.localVar = null;
                    ThreadLocalDemo.setForVisit(10000);
                    System.gc();
                    ThreadLocalDemo.setForVisit(500);
                    System.out.println(ThreadLocalDemo.getForVisit());
                    ThreadLocalDemo.removeForVisit();
                    for (int i = 0; i < 10; i++) {
                        new ThreadLocal<>().get();
                    }
                }
        ).start();
//        new Thread(
//                () -> {
//                    ThreadLocalDemo.setLocalVar(new Command("marry", 17));
//                    System.out.println(ThreadLocalDemo.localVar);
//                    System.out.println(ThreadLocalDemo.getLocalVar());
//                }
//        ).start();


//        // 创建一个对象并建立弱引用
//        Object obj = new Object();
//        WeakReference<Object> weakRef = new WeakReference<>(obj);
//
//        // 手动触发垃圾回收无事发生因为有强引用
//        System.gc();
//
//        // 对象仍然存在，可以正常使用
//        System.out.println("Object is still accessible cause it is a strong ref: " + weakRef.get());
//
//        // 解除对对象的强引用
//        obj = null;
//
//        // 弱引用还在
//        System.out.println("Object before garbage collection is still accessible cause gc has not collected it: " + weakRef.get());
//
//        // 手动触发垃圾回收，只有弱引用回收该对象
//        System.gc();
//
//        // 垃圾回收后，对象被回收，弱引用返回null
//        System.out.println("week ref Object after garbage collection: " + weakRef.get());

    }
}
