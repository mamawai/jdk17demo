package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.LinkedList;
import java.util.Queue;

public class 搜索二阶矩阵 {
    public static void main(String[] args) {
        boolean b = new Solution240().searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20);
        System.out.println(b);
    }
}

class Solution240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 右上角
        int x = 0;
        int y = matrix[0].length - 1;
        int X = x;
        int Y = y;
        while (true) {
            if (Y < 0 || X > matrix.length - 1)
                return false;
            if (matrix[x][y] == target) return true;
            if (target > matrix[x][y])
                X = x + 1;
            if (target < matrix[x][y])
                Y = y - 1;
            x = X;
            y = Y;
        }
    }
}