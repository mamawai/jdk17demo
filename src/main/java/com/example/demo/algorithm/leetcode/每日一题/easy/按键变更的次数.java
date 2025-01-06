package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.LinkedList;

public class 按键变更的次数 {
    public static void main(String[] args) {

    }
}

class Solution3019 {
    public int countKeyChanges(String s) {
        int res = 0;
        char[] charArr = s.toCharArray();
        LinkedList<Integer> stack = new LinkedList<>();
        for (char c : charArr) {
            int idx = c - 'a' >= 0 ? c - 'a' : c - 'A';
            if (stack.isEmpty()) stack.push(idx);
            if (stack.peek() != idx) {
                res++;
                stack.push(idx);
            }
        }
        return res;
    }
}