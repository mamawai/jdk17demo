package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 出租车的最大盈利 {
    public static void main(String[] args) {
        long i = new Solution2008C().maxTaxiEarnings(20, new int[][]{{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}});
    }
}

class Solution2008 {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);
        int m = rides.length;
        long[] dp = new long[m + 1];
        for (int i = 0; i < m; i++) {
            int j = binarySearch(rides, i, rides[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[j] + rides[i][1] - rides[i][0] + rides[i][2]);
        }
        return dp[m];
    }

    public int binarySearch(int[][] rides, int high, int target) {
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (rides[mid][1] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

class Solution2008B {
    public long maxTaxiEarnings(int n, int[][] rides) {
        List<int[]>[] groups = new ArrayList[n + 1];
        for (int[] r : rides) {
            int start = r[0], end = r[1], tip = r[2];
            if (groups[end] == null) {
                groups[end] = new ArrayList<>();
            }
            groups[end].add(new int[]{start, end - start + tip});
        }

        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(n, memo, groups);
    }

    private long dfs(int i, long[] memo, List<int[]>[] groups) {
        if (i == 1) {
            return 0;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }
        long res = dfs(i - 1, memo, groups);
        if (groups[i] != null) {
            for (int[] p : groups[i]) {
                res = Math.max(res, dfs(p[0], memo, groups) + p[1]);
            }
        }
        return memo[i] = res; // 记忆化
    }
}

class Solution2008C {
    public long maxTaxiEarnings(int n, int[][] rides) {
        List<int[]>[] groups = new ArrayList[n + 1];
        for (int[] r : rides) {
            int start = r[0], end = r[1], tip = r[2];
            if (groups[end] == null) {
                groups[end] = new ArrayList<>();
            }
            groups[end].add(new int[]{start, end - start + tip});
        }

        long[] f = new long[n + 1];
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1];
            if (groups[i] != null) {
                for (int[] p : groups[i]) {
                    f[i] = Math.max(f[i], f[p[0]] + p[1]);
                }
            }
        }
        return f[n];
    }
}