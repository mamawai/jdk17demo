package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 分割等和子集 {
    public static void main(String[] args) {
        boolean b = new Solution416().canPartition(new int[]{1,5,5,11});

        System.out.println(b);
    }
}

class Solution416 {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2!=0) return false;
        int target = total / 2;
        boolean[] f = new boolean[target + 1];
        f[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; --j) {
                f[j] = f[j] | f[j - num];
            }
        }
        return f[target];
    }
}

class Solution416B {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    // 状态转移表达式 （不选 | 选）
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    // 不选
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
    // 20250407第二次做
    static class Solution416C {
        public boolean canPartition(int[] nums) {
            Arrays.sort(nums);
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            if (total % 2 != 0) return false;
            int target = total / 2;
            return true;
        }
    }
}