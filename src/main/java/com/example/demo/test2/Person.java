package com.example.demo.test2;

import lombok.Data;
@Data
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person(){}
}

