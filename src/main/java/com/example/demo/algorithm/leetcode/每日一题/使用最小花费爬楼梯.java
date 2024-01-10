package com.example.demo.algorithm.leetcode.每日一题;

public class 使用最小花费爬楼梯 {
    public static void main(String[] args) {

    }
}

class Solutionshjak {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(cost[i-1] + dp[i-1] , cost[i-2] + dp[i - 2]);
        }
        return dp[n];
    }
}
