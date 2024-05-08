package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;

public class 摘樱桃 {
    public static void main(String[] args) {

    }
}

class Solution741 {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n * 2 - 1][n][n];
        for (int[][] m : memo) {
            for (int[] r : m) {
                Arrays.fill(r, -1); // -1 表示没有计算过
            }
        }
        return Math.max(dfs(n * 2 - 2, n - 1, n - 1, grid, memo), 0);
    }

    /**
     * @param t 步数
     * @param j 第一个人向下走的深度
     * @param k 第二个人向下走的深度
     */
    private int dfs(int t, int j, int k, int[][] grid, int[][][] memo) {
        // 不能出界，不能访问 -1 格子
        if (j < 0 || k < 0 || t < j || t < k || grid[t - j][j] < 0 || grid[t - k][k] < 0) {
            return Integer.MIN_VALUE;
        }
        if (t == 0) { // 此时 j = k = 0
            return grid[0][0];
        }
        if (memo[t][j][k] != -1) { // 之前计算过
            return memo[t][j][k];
        }
        int res = Math.max(
                Math.max(dfs(t - 1, j, k, grid, memo), dfs(t - 1, j, k - 1, grid, memo)),
                Math.max(dfs(t - 1, j - 1, k, grid, memo), dfs(t - 1, j - 1, k - 1, grid, memo)))
                + grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
        memo[t][j][k] = res; // 记忆化
        return res;
    }

}

class Solution741B {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] f = new int[n * 2 - 1][n + 1][n + 1];
        for (int[][] m : f) {
            for (int[] r : m) {
                Arrays.fill(r, Integer.MIN_VALUE);
            }
        }
        f[0][1][1] = grid[0][0];
        for (int t = 1; t < n * 2 - 1; t++) {
            for (int j = Math.max(t - n + 1, 0); j <= Math.min(t, n - 1); j++) {
                if (grid[t - j][j] < 0) continue;
                for (int k = j; k <= Math.min(t, n - 1); k++) {
                    if (grid[t - k][k] < 0) continue;
                    f[t][j + 1][k + 1] = Math.max(Math.max(f[t - 1][j + 1][k + 1], f[t - 1][j + 1][k]), Math.max(f[t - 1][j][k + 1], f[t - 1][j][k])) +
                            grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
                }
            }
        }
        return Math.max(f[n * 2 - 2][n][n], 0);
    }
}