package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 不同路径 {
    public static void main(String[] args) {
        int i = new Solution62().uniquePaths(3, 7);
        System.out.println(i);
    }
}

class Solution62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][0] = 1;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

}