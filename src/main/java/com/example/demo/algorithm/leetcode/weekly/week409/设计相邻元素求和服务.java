package com.example.demo.algorithm.leetcode.weekly.week409;

public class 设计相邻元素求和服务 {
    public static void main(String[] args) {
        neighborSum neighborSum = new neighborSum(new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}
        });
        System.out.println(neighborSum.adjacentSum(1));
        System.out.println(neighborSum.adjacentSum(4));
        System.out.println(neighborSum.diagonalSum(4));
        System.out.println(neighborSum.diagonalSum(8));

    }
}

class neighborSum {

    private final int[] adjacent;
    private final int[] diagonal;

    public neighborSum(int[][] grid) {
        int n = grid.length;
        adjacent = new int[n * n];
        diagonal = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int e = grid[i][j];
                // 左上
                if (i - 1 >= 0 && j - 1 >= 0) diagonal[e] += grid[i-1][j-1];
                // 上
                if (i - 1 >= 0) adjacent[e] += grid[i-1][j];
                // 右上
                if (i - 1 >= 0 && j + 1 < n) diagonal[e] += grid[i-1][j+1];
                // 左
                if (j - 1 >= 0) adjacent[e] += grid[i][j - 1];
                // 右
                if (j + 1 < n) adjacent[e] += grid[i][j + 1];
                // 下
                if (i + 1 < n) adjacent[e] += grid[i + 1][j];
                // 左下
                if (i + 1 < n && j - 1 >= 0) diagonal[e] += grid[i + 1][j-1];
                // 右下
                if (i + 1 < n && j + 1 < n) diagonal[e] += grid[i + 1][j + 1];
            }
        }
    }

    public int adjacentSum(int value) {
        return adjacent[value];
    }

    public int diagonalSum(int value) {
        return diagonal[value];
    }
}