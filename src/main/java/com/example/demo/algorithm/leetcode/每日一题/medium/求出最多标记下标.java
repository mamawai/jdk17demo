package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 求出最多标记下标 {
    public static void main(String[] args) {
        int i = new Solution2576().maxNumOfMarkedIndices(new int[]{57,40,57,51,90,51,68,100,24,39,11,85,2,22,67,29,74,82,10,96,14,35,25,76,26,54,29,44,63,49,73,50,95,89,43,62,24,88,88,36,6,16,14,2,42,42,60,25,4,58,23,22,27,26,3,79,64,20,92});
        System.out.println(i);
    }
}

class Solution2576 {
    public int maxNumOfMarkedIndices(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = n / 2;
        while (left < right && right < n && left < n / 2) {
            if (nums[left] * 2 <= nums[right]) {
                ans += 2;
                left++;
            }
            right++;
        }
        return ans;
    }
}