package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 检查数组是否存在有效划分 {
    public static void main(String[] args) {
        boolean b = new Solution2369().validPartition(new int[]{1, 1, 1, 3, 4, 5, 6});
    }
}

class Solution2369 {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i < n; i++) {
            if (f[i - 1] && nums[i] == nums[i - 1] ||
                    i > 1 && f[i - 2] && (nums[i] == nums[i - 1] && nums[i] == nums[i - 2] ||
                            nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2)) {
                f[i + 1] = true;
            }
        }
        return f[n];
    }
}

