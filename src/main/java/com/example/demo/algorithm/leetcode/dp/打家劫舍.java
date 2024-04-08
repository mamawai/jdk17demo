package com.example.demo.algorithm.leetcode.dp;

public class 打家劫舍 {
    public static void main(String[] args) {
        int rob = new Solution198().rob(new int[]{0});
        System.out.println(rob);
    }
}

class Solution198 {
    public int rob(int[] nums) {
//        if (nums.length == 1) return nums[0];
//        int[] dp = new int[nums.length + 1];
//        dp[1] = nums[0];
//        dp[2] = nums[1];
//        for (int i = 3; i <= nums.length; i++) {
//            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i - 1];
//        }
//        return Math.max(dp[nums.length], dp[nums.length - 1]);
        if (nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[N];
    }
}