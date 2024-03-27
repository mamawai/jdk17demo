package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 零钱兑换2 {
    public static void main(String[] args) {
        new Solution518B().change(6, new int[] {1,2,5});
    }
}

class Solution518 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}

class Solution518B {
    private int[] coins;
    private int[][] memo;

    public int change(int amount, int[] coins) {
        this.coins = coins;
        int n = coins.length;
        memo = new int[n][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有访问过
        }
        return dfs(n - 1, amount);
    }

    private int dfs(int i, int c) {
        if (i < 0) {
            return c == 0 ? 1 : 0;
        }
        if (memo[i][c] != -1) { // 之前算过了
            return memo[i][c];
        }
        if (c < coins[i]) {
            return memo[i][c] = dfs(i - 1, c);
        }
        return memo[i][c] = dfs(i - 1, c) + dfs(i, c - coins[i]);
    }
}