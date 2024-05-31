package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 目标和 {
    public static void main(String[] args) {
        int targetSumWays = new Solution494().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
    }
}

class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        // p正数
        // p - (s - p) = t
        // p = (t + s) / 2
        for (int x : nums) target += x;
        // target 一定被2整除否则直接返回0
        if (target < 0 || target % 2 == 1) return 0;
        target /= 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int x : nums) {
            for (int c = target; c >= x ;--c) {
                dp[c] += dp[c - x];
            }
        }
        return dp[target];

    }
}