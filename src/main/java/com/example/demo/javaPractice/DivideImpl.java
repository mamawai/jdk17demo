package com.example.demo.javaPractice;

import org.springframework.stereotype.Component;

@Component
public class DivideImpl implements Divide{
    @Override
    public int div(int i, int j) {
        try {
            System.out.println("开始div");
            int res = i / j;
            System.out.println("结束div");
            return res;
        } catch (Exception e) {
            System.out.println("catch住了");
            throw new RuntimeException(e);
        }
    }
}
