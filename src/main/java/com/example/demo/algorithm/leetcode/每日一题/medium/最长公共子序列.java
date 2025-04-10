package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 最长公共子序列 {
    public static void main(String[] args) {
        int i = new Solution1143().longestCommonSubsequence("dcaabcde", "ace");
        System.out.println(i);
    }
}


class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 状态转移方程：dp[i][j] = dp[i - 1][j - 1] + 1, if text1[i - 1] == text2[j - 1];
        // dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]), if text1[i - 1] != text2[j - 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}