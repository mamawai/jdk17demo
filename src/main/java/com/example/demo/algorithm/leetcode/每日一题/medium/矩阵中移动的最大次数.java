package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 矩阵中移动的最大次数 {
    public static void main(String[] args) {
        int i = new Solution2684().maxMoves(new int[][]{{2, 4, 3, 5}, {5, 4, 9, 3}, {3, 4, 2, 11}, {10, 9, 13, 15}});
        System.out.println(i);
    }
}

class Solution2684 {
    public int maxMoves(int[][] grid) {
        int max = 0;
        int[][] memo = new int[grid.length][grid[0].length];
        int[][] offsets = new int[][]{{-1,1},{0,1},{1,1}};
        for (int j = 0; j < grid.length; j++) {
                int value = grid[j][0];
                max = Math.max(max, dfs(value, offsets, memo, grid, j, 0));
        }
        return max - 1;
    }

    private int dfs(int value, int[][] offsets, int[][] memo, int[][] grid, int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        // offset查找
        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];
            // 如果越界
            if (newX < 0 || newX == grid.length) {
                continue;
            }
            // 如果此时到右侧墙壁那么memo记录为1并返回
            else if (newY == grid[0].length) {
                return memo[x][y] = 1;
            } else {
                int newValue = grid[newX][newY];
                if (newValue > value) {
                    // dfs
                    memo[x][y] = Math.max(memo[x][y], dfs(newValue, offsets, memo, grid, newX, newY) + 1);
                } else {
                    memo[x][y] = Math.max(memo[x][y], 1);
                }
            }
        }
        return memo[x][y];
    }
}