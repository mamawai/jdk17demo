package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;
import java.util.Map;

public class 摘樱桃2 {
    public static void main(String[] args) {
        int i = new Solution1463().cherryPickup(new int[][]{{1, 0, 0, 0, 0, 0, 1}, {2, 0, 0, 0, 0, 3, 0}, {2, 0, 9, 0, 0, 0, 0}, {0, 3, 0, 5, 4, 0, 0}, {1, 0, 2, 3, 0, 0, 6}});
        System.out.println(i);
    }
}

class Solution1463 {
    public int cherryPickup(int[][] grid) {
        // 左右坐标 i & j 纵坐标一样
        // f + Max(1,2,3) + Max(1,2,3) if i == j 除以2
        int n = grid.length;
        int[][][]  memo = new int[n][grid[0].length][grid[0].length];
        for (int[][] m : memo) {
            for (int[] r : m) {
                Arrays.fill(r, -1);
             }
        }
        return dfs(0, 0, grid[0].length - 1, grid, memo);
    }

    private int dfs(int depth, int i, int j, int[][] grid, int[][][] memo) {
        // 如果越界返回 -1
        if (i < 0 || j > grid[0].length - 1 || j < 0 || i > grid[0].length - 1) return -1;
        if (depth == grid.length - 1) {
            return memo[depth][i][j] = grid[depth][i] + (i != j ? grid[depth][j] : 0);
        }
        // 记忆化返回
        if (memo[depth][i][j] != -1) return memo[depth][i][j];

        int res = grid[depth][i] + (i != j ? grid[depth][j] : 0) +
                Math.max(
                        Math.max(
                                Math.max(
                                        Math.max(dfs(depth + 1, i - 1, j - 1, grid, memo), dfs(depth + 1, i - 1, j, grid, memo)),
                                        Math.max(dfs(depth + 1, i - 1, j + 1, grid, memo), dfs(depth + 1, i + 1, j - 1, grid, memo))
                                ),
                                Math.max(
                                        Math.max(dfs(depth + 1, i + 1, j, grid, memo), dfs(depth + 1, i + 1, j + 1, grid, memo)),
                                        Math.max(dfs(depth + 1, i, j - 1, grid, memo), dfs(depth + 1, i, j + 1, grid, memo))
                                )
                        ), dfs(depth + 1, i, j, grid, memo)
                );
        memo[depth][i][j] = res;
        return res;
    }
}