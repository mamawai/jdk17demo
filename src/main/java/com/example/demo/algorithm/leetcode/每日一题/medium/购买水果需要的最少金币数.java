package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 购买水果需要的最少金币数 {
    public static void main(String[] args) {
        int i = new Solution2944().minimumCoins(new int[]{14,37,37,38,24,15,12});
        System.out.println(i);
    }
}

class Solution2944 {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        if (n == 1) return prices[0];
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[1] = prices[0];
        f[2] = prices[0];
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= Math.min(2 * (i + 1), n); j++) {
                if (j > i + 1 && f[j] < f[i] + prices[i]) break;
                f[j] = Math.min(f[j], f[i] + prices[i]);
            }
        }
        return f[n];
    }
}