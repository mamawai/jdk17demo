package com.example.demo.algorithm.leetcode;

import java.util.Arrays;

public class 网格中的最小路径代价 {
    public static void main(String[] args) {
        int minPathCost = new Solution2304().minPathCost(new int[][]{{5, 3}, {4, 0}, {2, 1}, {6,7}}, new int[][]{{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}, {4,10}, {9,5}});
        System.out.println(minPathCost);
    }
}

class Solution2304 {
    // 记忆化搜索 memo记录当前位置的最小值
    public int[][] memo;

    // i剩余层数 j当前层数元素的位置
    public int dfs(int i, int j, int[][] grid, int[][] moveCost) {
        if (i == grid.length - 1) {
            return grid[i][j];
        }
        if (memo[i][j] >= 0) {
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        // k为当前层的元素位置
        for (int k = 0; k < grid[0].length; k++) {
            res = Math.min(res, dfs(i + 1, k, grid, moveCost) + grid[i][j] + moveCost[grid[i][j]][k]);
        }
        memo[i][j] = res;
        return res;
    }
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dfs(0, j, grid, moveCost));
        }
        return res;
    }


}