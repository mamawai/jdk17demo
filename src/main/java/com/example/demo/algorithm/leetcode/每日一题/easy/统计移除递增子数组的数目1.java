package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.LinkedList;

public class 统计移除递增子数组的数目1 {
    public static void main(String[] args) {
        int i = new Solution2970().incremovableSubarrayCount(new int[]{5,3,4,6,7});
        System.out.println(i);
    }
}

class Solution2970 {
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int res = 0;
        int i = 0;
        // 统计从左数一直单调递增的位置
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i == n - 1) { // 每个非空子数组都可以移除
            return n * (n + 1) / 2;
        }
        res = i + 2;
        for (int j = n - 1; j == n - 1 || nums[j] < nums[j + 1]; j--) {
            while (i >= 0 && nums[i] >= nums[j]) {
                i--;
            }
            // 可以保留前缀 a[:i+1], a[:i], ..., a[:0] 一共 i+2 个
            res += i + 2;
        }
        return res;
    }
}