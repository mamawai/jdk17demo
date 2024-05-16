package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.HashMap;
import java.util.Map;

public class 吃掉N个橘子的最少天数 {
    public static void main(String[] args) {

    }
}

class Solution1553 {
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        Integer res = memo.get(n);
        if (res != null) { // 之前计算过
            return res;
        }
        res = Math.min(minDays(n / 2) + n % 2, minDays(n / 3) + n % 3) + 1;
        memo.put(n, res); // 记忆化
        return res;
    }
}