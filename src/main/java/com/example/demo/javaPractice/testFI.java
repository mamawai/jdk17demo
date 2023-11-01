package com.example.demo.javaPractice;

import java.util.function.Predicate;

public class testFI {
    public static void main(String[] args) {
        Predicate predicate  = o -> (boolean) o;
        boolean test = predicate.test(true);
        System.out.println(test);
    }
}