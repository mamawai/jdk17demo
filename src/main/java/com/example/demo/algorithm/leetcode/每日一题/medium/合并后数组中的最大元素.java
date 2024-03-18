package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 合并后数组中的最大元素 {
    public static void main(String[] args) {

    }
}

class Solution2789 {
    public long maxArrayValue(int[] nums) {
        long ans = nums[nums.length-1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= ans) {
                ans += nums[i];
            } else {
                ans = nums[i];
            }
        }
        return ans;
    }
}
