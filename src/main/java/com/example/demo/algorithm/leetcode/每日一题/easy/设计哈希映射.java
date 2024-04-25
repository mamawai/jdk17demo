package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.Arrays;

public class 设计哈希映射 {
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();

    }
}

class MyHashMap {
    int[] space;

    public MyHashMap() {
        space = new int[100000];
        Arrays.fill(space, -1);
    }

    public void put(int key, int value) {
        space[key] = value;
    }

    public int get(int key) {
        return space[key];
    }

    public void remove(int key) {
        space[key] = -1;
    }
}