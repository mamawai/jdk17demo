package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 保持城市天际线 {
    public static void main(String[] args) {

    }
}

class Solution807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int icr = 0;
        int[] rowMaxArr = new int[n];
        for (int i = 0; i < n; i++) {
            int[] row = grid[i];
            int rowMax = 0;
            int rowSum = 0;
            for (int e : row) {
                rowSum += e;
                rowMax = Math.max(e, rowMax);
            }
            rowMaxArr[i] = rowMax;
            icr += rowMax * n - rowSum;
        }
        for (int i = 0; i < n; i++) {
            int colMax = 0;
            for (int[] row : grid) {
                colMax = Math.max(colMax, row[i]);
            }
            for (int rowMax : rowMaxArr) {
                if (colMax < rowMax) icr -= (rowMax - colMax);
            }
        }
        return icr;
    }
}