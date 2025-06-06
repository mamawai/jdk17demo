package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 对角线上不同值的数量差 {
    public static void main(String[] args) {
        int[][] ints = new Solution2711().differenceOfDistinctValues(new int[][]{
                {1, 2, 3},
                {3, 1, 5},
                {3, 2, 1}});
        System.out.println(Arrays.deepToString(ints));
    }
}

class Solution2711 {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];
        Set<Integer> set = new HashSet<>();
        // 第一排在右上，最后一排在左下
        // 每排从左上到右下
        // 令 k=i-j+n，那么右上角 k=1，左下角 k=m+n-1
        for (int k = 1; k < m + n; k++) {
            // 核心：计算 j 的最小值和最大值
            int minJ = Math.max(n - k, 0); // i=0 的时候，j=n-k，但不能是负数
            int maxJ = Math.min(m + n - 1 - k, n - 1); // i=m-1 的时候，j=m+n-1-k，但不能超过 n-1

            set.clear();
            for (int j = minJ; j <= maxJ; j++) {
                int i = k + j - n;
                ans[i][j] = set.size(); // topLeft[i][j] == set.size()
                set.add(grid[i][j]);
            }

            set.clear();
            for (int j = maxJ; j >= minJ; j--) {
                int i = k + j - n;
                ans[i][j] = Math.abs(ans[i][j] - set.size()); // bottomRight[i][j] == set.size()
                set.add(grid[i][j]);
            }
        }
        return ans;
    }
}