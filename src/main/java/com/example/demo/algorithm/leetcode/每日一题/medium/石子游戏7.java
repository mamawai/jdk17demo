package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 石子游戏7 {
    public static void main(String[] args) {
        int i = new SolutionB1690().stoneGameVII(new int[]{792,195,697,271,743,51,836,322,135,550,399,182,988,25,395,254,480,931,513,772,798,102,110,915,794,330,597,220,789,462});
        System.out.println(i);
    }
}

// dfs+记忆化搜索
class Solution1690 {
    public int stoneGameVII(int[] stones) {
        int curSum = Arrays.stream(stones).sum();
        int memo[][] = new int[stones.length][stones.length];
        return dfs(0,  stones.length - 1, stones, curSum, memo);
    }

    private int dfs(int left, int right, int[] stones, int curSum, int[][] memo) {
        if (left == right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int res1 = curSum - stones[left] - dfs(left + 1, right, stones, curSum - stones[left], memo);
        int res2 = curSum - stones[right] - dfs(left, right - 1, stones, curSum - stones[right], memo);
        return memo[left][right] = Math.max(res1, res2);
    }
}

// dp
class SolutionB1690 {
    // f[i][j]=max(s[j+1]−s[i+1]−f[i+1][j], s[j]−s[i]−f[i][j−1])
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 求前缀和
            // 1 2 3 4 5
            // 0 1 3 6 10 15
            s[i + 1] += s[i] + stones[i];
        }
        int[][] f = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(s[j + 1] - s[i + 1] - f[i + 1][j], s[j] - s[i] - f[i][j - 1]);
            }
        }
        return f[0][n - 1];
    }
}