package com.example.demo.proxyJDK;

public class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }
    @Override
    public void giveMoney(int money) {
        try {
            // 假设数钱花了一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "上交班费"+money+"元");
    }
}