package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.concurrent.locks.ReentrantLock;

public class 使所有字符相等的最小成本 {
    public static void main(String[] args) {

    }
}

class Solution2712 {
    public long minimumCost(String s) {
        char[] S = s.toCharArray();
        int n = S.length;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            if (S[i - 1] != S[i]) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }
}