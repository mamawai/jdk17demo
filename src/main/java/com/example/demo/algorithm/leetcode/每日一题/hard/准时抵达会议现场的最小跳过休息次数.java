package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;

public class 准时抵达会议现场的最小跳过休息次数 {
    public static void main(String[] args) {
        int i = new Solution1883().minSkips(new int[]{7, 3, 5, 5}, 2, 10);

    }
}

class Solution1883 {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        // 以dist=[7,3,5,5] 为例，如果最多跳 2 次，用「选或不选」分类讨论：
        // 不跳过 dist[n−2]问题变成在最多跳过 2 次的情况下，经过 [7,3] 需要的最小时间。
        // 跳过 dist[n−2]问题变成在最多跳过 1 次的情况下，经过 [7,3] 需要的最小时间。

        int sumDist = 0;
        for (int d : dist) {
            sumDist += d;
        }
        if (sumDist > (long) speed * hoursBefore) {
            return -1;
        }

        int n = dist.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        for (int i = 0; ; i++) {
            if (dfs(i, n - 2, memo, dist, speed) + dist[n - 1] <= (long) speed * hoursBefore) {
                return i;
            }
        }
    }

    private int dfs(int i, int j, int[][] memo, int[] dist, int speed) {
        if (j < 0) { // 递归边界
            return 0;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        // 向上取整转向下取整
        // a/b向上取整 = a+b-1/b向下取整
        int res = (dfs(i, j - 1, memo, dist, speed) + dist[j] + speed - 1) / speed * speed;
        if (i > 0) {
            res = Math.min(res, dfs(i - 1, j - 1, memo, dist, speed) + dist[j]);
        }
        return memo[i][j] = res; // 记忆化
    }
}

// 1:1 翻译成递推
// 我们可以去掉递归中的「递」，只保留「归」的部分，即自底向上计算。
class Solution1883B {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int sumDist = 0;
        for (int d : dist) {
            sumDist += d;
        }
        if (sumDist > (long) speed * hoursBefore) {
            return -1;
        }

        // 由于上面的dfs会递归到j = -1的边界，这里将j = 0视为上面dfs的 j = -1; 所以就会出现f[i][j + 1]
        int n = dist.length;
        int[][] f = new int[n][n];
        // i表示最小次数
        for (int i = 0; ; i++) {
            for (int j = 0; j < n - 1; j++) {
                f[i][j + 1] = (f[i][j] + dist[j] + speed - 1) / speed * speed;
                if (i > 0) {
                    f[i][j + 1] = Math.min(f[i][j + 1], f[i - 1][j] + dist[j]);
                }
            }
            // 递归边界 在递归中第一次满足这个不等式就返回
            if (f[i][n - 1] + dist[n - 1] <= (long) speed * hoursBefore) {
                return i;
            }
        }
    }
}