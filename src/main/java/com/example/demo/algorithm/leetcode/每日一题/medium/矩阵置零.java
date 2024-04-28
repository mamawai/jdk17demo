package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 矩阵置零 {
    public static void main(String[] args) {
        new Solution73().setZeroes(new int[][]{{1,1,1},{1,0,1},{1,1,1}});
    }
}

class Solution73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}