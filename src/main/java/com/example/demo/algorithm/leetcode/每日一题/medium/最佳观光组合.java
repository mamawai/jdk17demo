package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.PriorityQueue;

public class 最佳观光组合 {
    public static void main(String[] args) {

    }
}

class Solution1014 {
    public int maxScoreSightseeingPair(int[] values) {
        // values[i] + i + values[j] - j
        int ans = 0;
        int mx = values[0];
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, mx + values[i] - i);// 计算当前的最大值
            mx = Math.max(mx, values[i] + i);// 维护i左侧的最大值， 看当前的i和之前mx谁更大
        }
        return ans;
    }
}