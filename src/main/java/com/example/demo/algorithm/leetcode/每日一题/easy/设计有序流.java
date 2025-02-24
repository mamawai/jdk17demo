package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */
public class 设计有序流 {
    public static void main(String[] args) {
        OrderedStream obj = new OrderedStream(5);
        List<String> param_1 = obj.insert(3, "ccccc");
        System.out.println(param_1);
        param_1 = obj.insert(1, "aaaaa");
        System.out.println(param_1);
        param_1 = obj.insert(2, "bbbbb");
        System.out.println(param_1);
        param_1 = obj.insert(5, "eeeee");
        System.out.println(param_1);
        param_1 = obj.insert(4, "ddddd");
        System.out.println(param_1);
    }
}

class OrderedStream {

    private int ptr;

    private String[] stream;

    public OrderedStream(int n) {
        ptr = 1;
        stream = new String[n];
    }

    public List<String> insert(int idKey, String value) {
        List<String> res = new ArrayList<>();
        stream[idKey - 1] = value;
        if (idKey == ptr && stream[idKey - 1] != null) {
            while (ptr <= stream.length && stream[ptr - 1] != null) {
                res.add(stream[ptr++ - 1]);
            }
        }
        return res;
    }
}