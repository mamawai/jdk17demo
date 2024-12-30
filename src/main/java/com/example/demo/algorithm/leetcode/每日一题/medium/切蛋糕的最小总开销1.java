package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 切蛋糕的最小总开销1 {
    public static void main(String[] args) {
//        int cost = new Solution3218().minimumCost(6, 3, new int[]{2,3,2,3,1}, new int[]{1,2});
        long cost = new Solution3218B().minimumCost(3, 2, new int[]{1,3}, new int[]{5});
        System.out.println(cost);
    }
}


class Solution3218 {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut); // 下面倒序遍历
        Arrays.sort(verticalCut);
        long ans = 0;
        int i = 0;
        int j = 0;
        while (i < m - 1 || j < n - 1) {
            if (j == n - 1 || i < m - 1 && horizontalCut[i] < verticalCut[j]) {
                ans += (long) horizontalCut[i++] * (n - j); // 上下连边
            } else {
                ans += (long) verticalCut[j++] * (m - i); // 左右连边
            }
        }
        return ans;
    }
}

class Solution3218C {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int ans = 0;
        int i = m - 2, j = n - 2;
        int h = 1, v = 1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && horizontalCut[i] > verticalCut[j])) {
                ans += horizontalCut[i--] * v;
                ++h;
            } else {
                ans += verticalCut[j--] * h;
                ++v;
            }
        }
        return ans;
    }
}

class Solution3218B {
    int[][][][] memo;
    int[] horizontalCut;
    int[] verticalCut;

    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        this.memo = new int[m][n][m][n];
        this.horizontalCut = horizontalCut;
        this.verticalCut = verticalCut;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }
        return dp(0, 0, m - 1, n - 1);
    }

    public int dp(int row1, int col1, int row2, int col2) {
        if (row1 == row2 && col1 == col2) {
            return 0;
        }
        if (memo[row1][col1][row2][col2] < 0) {
            int res = Integer.MAX_VALUE;
            for (int i = row1; i < row2; i++) {
                res = Math.min(res, dp(row1, col1, i, col2) + dp(i + 1, col1, row2, col2) + horizontalCut[i]);
            }
            for (int i = col1; i < col2; i++) {
                res = Math.min(res, dp(row1, col1, row2, i) + dp(row1, i + 1, row2, col2) + verticalCut[i]);
            }
            memo[row1][col1][row2][col2] = res;
        }
        return memo[row1][col1][row2][col2];
    }
}