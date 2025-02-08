package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 不同路径2 {
    public static void main(String[] args) {
        int i = new Solution63().uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}});
        System.out.println(i);
    }
}

class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 障碍物 continue
                if (obstacleGrid[i][j] == 1 || i == 0 && j == 0) continue;
                // 加上该格子左和上的路径数量
                int left = (j - 1 < 0 || obstacleGrid[i][j - 1] == 1) ? 0 : f[i][j - 1];
                int up = (i - 1 < 0 || obstacleGrid[i - 1][j] == 1) ? 0 : f[i - 1][j];
                f[i][j]  = left + up;
            }
        }
        return f[m - 1][n - 1];
    }
}