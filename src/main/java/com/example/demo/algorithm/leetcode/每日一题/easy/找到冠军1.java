package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayList;
import java.util.List;

public class 找到冠军1 {
    public static void main(String[] args) {

    }
}

class Solution2923 {
    public int findChampion(int[][] grid) {
        int champion = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 1) break;
                if (j == grid.length - 1) {
                    return champion = i;
                }
            }
        }
        return champion;
    }
}