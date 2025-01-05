package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Map;
import java.util.TreeMap;

public class 我的日程表安排3 {
    public static void main(String[] args) {

    }
}

class MyCalendarThree {
    private TreeMap<Integer, Integer> cnt;

    public MyCalendarThree() {
        cnt = new TreeMap<Integer, Integer>();
    }

    public int book(int start, int end) {
        int ans = 0;
        int maxBook = 0;
        cnt.put(start, cnt.getOrDefault(start, 0) + 1);
        cnt.put(end, cnt.getOrDefault(end, 0) - 1);
        // 差分
        // 比如 [10,30) 就说明 10 到 +∞ 的 最大标记是 1，30 到 +∞ 的 最大标记是 0， 所以最大标记就是 1
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int freq = entry.getValue();
            maxBook += freq;
            ans = Math.max(maxBook, ans);
        }
        return ans;
    }
}