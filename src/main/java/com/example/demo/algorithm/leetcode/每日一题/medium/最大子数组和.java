package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 最大子数组和 {
    public static void main(String[] args) {
        int i = new Solution53().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
    }
}

class Solution53 {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        // fn = Math.max(fn-1 + nums[n], nums[n]);
        int[] dp = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(dp[i + 1] = Math.max(dp[i] + nums[i], nums[i]), max);
        }
        return max;
    }
}