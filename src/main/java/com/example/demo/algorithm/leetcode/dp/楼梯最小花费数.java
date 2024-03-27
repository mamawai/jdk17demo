package com.example.demo.algorithm.leetcode.dp;

public class 楼梯最小花费数 {
    public static void main(String[] args) {
        int i = new Solution().minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1});
        System.out.println(i);
    }
}

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i <= cost.length; i++) {
            int min = Math.min(dp[i - 1], dp[i - 2]);
            if (i == cost.length) {
                dp[i] = min;
            } else {
                dp[i] = min +  cost[i];
            }
        }
        return dp[cost.length];
    }
}