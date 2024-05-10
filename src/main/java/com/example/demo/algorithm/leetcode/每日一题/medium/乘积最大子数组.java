package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 乘积最大子数组 {
    public static void main(String[] args) {
        int i = new Solution152().maxProduct(new int[]{-2,0,-14});
        System.out.println(i);
    }
}

class Solution152 {
    private int[] nums;
    private int[][] memos;
    public int maxProduct(int[] nums) {
        this.nums = nums;
        memos = new int[nums.length][2];
        for (int[] memo : memos) {
            memo[0] = Integer.MIN_VALUE;
            memo[1] = Integer.MAX_VALUE;
        }
        memos[0][0] = nums[0];
        memos[0][1] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = Math.max(ans, dfs(i)[0]);
        }
        return ans;
    }

    private int[] dfs(int i) {
        if (memos[i][0] != Integer.MIN_VALUE && memos[i][1] != Integer.MAX_VALUE) {
            return new int[]{memos[i][0], memos[i][1]};
        }
        int[] pair = dfs(i - 1);
        int pairMax = nums[i] * pair[0];
        int pairMin = nums[i] * pair[1];
        memos[i][0] = Math.max(Math.max(pairMax, memos[i][0]), Math.max(pairMin, nums[i]));
        memos[i][1] = Math.min(Math.min(pairMax, memos[i][1]), Math.min(pairMin, nums[i]));
        return new int[]{memos[i][0], memos[i][1]};
    }
}