package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.lang.reflect.Array;
import java.util.Arrays;

public class 寻找数组的中心下标 {
    public static void main(String[] args) {
        int pivotIndex = new Solution724().pivotIndex(new int[]{1, 7, 3, 6, 5, 6});
        System.out.println(pivotIndex);
    }
}

class Solution724 {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) leftSum += nums[i - 1];
            sum -= nums[i];
            if (leftSum == sum) return i;
        }
        return -1;
    }
}