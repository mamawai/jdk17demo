package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.math3.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class 最小路径和 {
    public static void main(String[] args) {
        int i = new Solution64().minPathSum(new int[][]{{1,2,3}, {4, 5, 6}});
        System.out.println(i);
    }
}

class Solution64 {
    private int[][] grid;
    int[][] memo;

    public int minPathSum(int[][] grid) {
        // f[i][j] = Math.min(f[i-1][j], f[i][j-1]) + grid[i][j];
        // 只能往右下走
        memo = new int[grid.length][grid[0].length];
        for (int[] m : memo) {
            Arrays.fill(m , -1);
        }
        this.grid = grid;
        return dfs(grid.length - 1, grid[0].length - 1);
    }

    private int dfs(int x, int y) {
        if (memo[x][y] != -1) return memo[x][y];
        if (x == 0 && y == 0) return memo[x][y] = grid[0][0];
        else if (x == 0) return memo[x][y] = dfs(x, y - 1) + grid[0][y];
        else if (y == 0) return memo[x][y] = dfs(x - 1, y) + grid[x][0];
        else return memo[x][y] = Math.min(dfs(x - 1, y), dfs(x, y - 1)) + grid[x][y];
    }
}