package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 最小差值2 {
    public static void main(String[] args) {
        int i = new Solution910().smallestRangeII(new int[]{7,8,8}, 5);
        System.out.println(i);
    }
}

class Solution910 {
    public int smallestRangeII(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] - k;
        }
        Arrays.sort(nums);
        int upMin = nums[0] + 2 * k;
        int upMax;
        int downMax = nums[n - 1];
        int downMin;
        int res = nums[n - 1] - nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            upMax = nums[i] + 2 * k;
            downMin = nums[i + 1];
            res = Math.min(res, Math.max(Math.max(upMin, downMin), Math.max(upMax, downMax))
                - Math.min(Math.min(upMax, downMax), Math.min(upMin, downMin)));
        }
        return res;
    }
}