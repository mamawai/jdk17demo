package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 构造相同颜色的正方形 {
    public static void main(String[] args) {

    }
}

class Solution3127 {
    public boolean canMakeSquare(char[][] grid) {
        int[][] offsets = new int[][]{{0, 0},{0, 1}, {1, 0},{1, 1}};
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid.length - 1; j++) {
                int wCnt = 0;
                int bCnt = 0;
                for (int[] offset : offsets) {
                    if (grid[i + offset[0]][j + offset[1]] == 'W')  wCnt++;
                    else bCnt++;
                }
                if (wCnt >= 3 || bCnt >= 3) return true;
            }
        }
        return false;
    }
}