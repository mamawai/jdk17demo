package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 相同分数的最大操作数目2 {
    public static void main(String[] args) {
        int i = new Solution3040().maxOperations(new int[]{3,2,1,2,3,4});
        System.out.println(i);
    }
}

class Solution3040 {
    private int[] nums;
    private int[][] memo;

    public int maxOperations(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        int res1 = dfs(2, n - 1, nums[0] + nums[1]); // 删除前两个数
        int res2 = dfs(0, n - 3, nums[n - 2] + nums[n - 1]); // 删除后两个数
        int res3 = dfs(1, n - 2, nums[0] + nums[n - 1]); // 删除第一个和最后一个数
        return Math.max(Math.max(res1, res2), res3) + 1; // 加上第一次操作
    }

    private int dfs(int i, int j, int target) {
        if (i >= j) {
            return 0;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int res = 0;
        if (nums[i] + nums[i + 1] == target) { // 删除前两个数
            res = Math.max(res, dfs(i + 2, j, target) + 1);
        }
        if (nums[j - 1] + nums[j] == target) { // 删除后两个数
            res = Math.max(res, dfs(i, j - 2, target) + 1);
        }
        if (nums[i] + nums[j] == target) { // 删除第一个和最后一个数
            res = Math.max(res, dfs(i + 1, j - 1, target) + 1);
        }
        return memo[i][j] = res; // 记忆化
    }
}