package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.math3.stat.StatUtils;

import java.util.Arrays;

public class 分割数组的方案数 {
    public static void main(String[] args) {

    }
}

class Solution2270 {
    public int waysToSplitArray(int[] nums) {
        int ans = 0;
        long sum = 0;
        for (int num : nums) sum += num;
        long left = 0;
        long right = sum;
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            left += num;
            right -= num;
            if (left >= right) ans++;
        }
        return ans;
    }
}