package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.sun.source.tree.BreakTree;

import java.util.Arrays;

public class 正整数和负整数的最大计数 {
    public static void main(String[] args) {
        int i = new Solution2529().maximumCount(new int[]{-2,-2,-1});
        System.out.println(i);
    }
}

/**
 * 时间复杂度O(logN)
 */
class Solution2529 {
    public int maximumCount(int[] nums) {
        if (nums[0] == 0 && nums[nums.length - 1] == 0) return 0;
        if (nums[nums.length - 1] < 0 ) return nums.length;
        int neg = lowerBound(nums, 0);
        // 第一个 > 0 的位置，等价于第一个 >= 1 的位置
        int pos = nums.length - lowerBound(nums, 1);
        return Math.max(neg, pos);
    }
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}