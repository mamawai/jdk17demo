package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.List;

public class 矩阵中的最大得分 {
    public static void main(String[] args) {

    }
}

class Solution3148 {
    public int maxScore(List<List<Integer>> grid) {
        int ans = -100000;
        // 问题转化 就是找一个格子到其右下方的格子 使这两个格子的差值最大
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dp = new int[m][n];
        // 从右下角开始dp dp[i][j]表示格子(i,j)到右下角的最大值
        for (int i = m - 1; i >= 0; i--) {
            List<Integer> row = grid.get(i);
            for (int j = n - 1; j >= 0; j--) {
                int num = row.get(j);
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = num;
                } else if (i == m - 1) {
                    // 最后一行
                    dp[i][j] = Math.max(dp[i][j + 1], num);
                    ans = Math.max(ans, dp[i][j + 1] - num);
                } else if (j == n - 1) {
                    // 最后一列
                    dp[i][j] = Math.max(dp[i + 1][j], num);
                    ans = Math.max(ans, dp[i + 1][j] - num);
                } else {
                    // 普通格子
                    int max = Math.max(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(max, num);
                    ans = Math.max(ans, max - num);
                }
            }
        }
        return ans;
    }
}