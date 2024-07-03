package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class 给墙壁刷油漆 {
    public static void main(String[] args) {
        int i = new Solution2742().paintWalls(new int[]{42,8,28,35,21,13,21,35}, new int[]{2,1,1,1,2,1,1,2});
        System.out.println(i);
    }
}

class Solution2742 {
    int[] cost;
    int[] time;
    int n;
    int[][] f;
    public int paintWalls(int[] cost, int[] time) {
        this.cost = cost;
        this.time = time;
        n = cost.length;
        f = new int[n][2 * n];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }
        // i是当前第i堵墙 j是免费刷墙的次数 -1相当于使用一次免费刷 起始值是n-1代表着0次免费刷次数
        // 由于你第一个可以免费刷 后面再付费刷 所以避免数组越界 需要定义成 n-1 由于你不可能所有的墙壁都免费刷至少要有一个付费刷
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {

        // 说明后面的墙全可以免费刷
        if (n - i <= j - n + 1) {
            return 0;
        }
        // 越界判定
        if (i >= n) return 1 << 30;
        // 不可能n个墙全都免费刷 j==0说明 n-1个免费刷
        if (j < 0) return 1 << 30;

        if (f[i][j] == -1) {
            f[i][j] = Math.min(dfs(i + 1, j + time[i]) + cost[i], dfs(i + 1, j - 1));
        }
        return f[i][j];
    }
}