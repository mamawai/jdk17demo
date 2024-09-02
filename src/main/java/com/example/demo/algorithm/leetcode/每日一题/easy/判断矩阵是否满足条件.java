package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 判断矩阵是否满足条件 {
    public static void main(String[] args) {
        boolean b = new Solution3142().satisfiesConditions(new int[][]{
                {1, 0, 2},
                {1, 0, 2}
        });
        System.out.println(b);
    }
}

class Solution3142 {
    public boolean satisfiesConditions(int[][] grid) {
        int[] sample = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            if (i == 0) {
                int j = 0;
                for (; j < row.length - 1; j++) {
                    int k = j + 1;
                    if (row[j] == row[k]) return false;
                    sample[j] = row[j];
                }
                sample[j] = row[j];
            } else {
                for (int j = 0; j < row.length; j++) {
                    if (row[j] != sample[j]) return false;
                }
            }
        }
        return true;
    }
}