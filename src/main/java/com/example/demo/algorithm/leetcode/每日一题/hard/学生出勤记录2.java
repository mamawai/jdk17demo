package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 学生出勤记录2 {
    public static void main(String[] args) {
        int checkRecord = new Solution552().checkRecord(1000);
        System.out.println(checkRecord);
    }
}

class Solution552 {
    private static final int MOD = 1_000_000_007;
    private static final int MX = 100_001;
    // 记忆化搜索
    private static final int[][][] memo = new int[MX][2][3];

    public int checkRecord(int n) {
        return dfs(n, 0, 0);
    }

    // i表示第i天，j表示今天是否是A，k表示之后连续L的天数 比如k=2那么就不能再放L了
    private int dfs(int i, int j, int k) {
        if (i == 0) {
            return 1;
        }
        if (memo[i][j][k] > 0) {
            return memo[i][j][k];
        }
        // 填P
        long res = dfs(i - 1, j, 0);
        if (j == 0) {
            // 填A
            res += dfs(i - 1, 1, 0);
        }
        // 填L
        if (k < 2) {
            res += dfs(i - 1, j, k + 1);
        }
        return memo[i][j][k] = (int) (res % MOD);
    }
}