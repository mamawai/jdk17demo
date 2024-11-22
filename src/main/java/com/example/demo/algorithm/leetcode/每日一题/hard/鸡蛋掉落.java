package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 鸡蛋掉落 {
    public static void main(String[] args) {
        int i = new Solution887().superEggDrop(2, 15);
    }
}

class Solution887 {
    public int superEggDrop(int k, int n) {
        int[][] memo = new int[n + 1][];
        for (int i = 1; ; i++) { // i表示扔的次数
            memo[i] = new int[k + 1]; // 动态创建 memo
            if (dfs(i, k, memo) >= n) { // dfs计算的是扔i次k个鸡蛋，最多能确定的楼层数
                return i;
            }
        }
    }

    private int dfs(int i, int j, int[][] memo) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (memo[i][j] != 0) { // 之前计算过
            return memo[i][j];
        }
        return memo[i][j] = dfs(i - 1,j, memo)+dfs(i - 1,j - 1, memo) + 1; // 前一个是鸡蛋没碎，后一个是鸡蛋碎了
    }
}