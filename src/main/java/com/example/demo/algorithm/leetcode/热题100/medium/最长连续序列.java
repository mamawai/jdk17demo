package com.example.demo.algorithm.leetcode.热题100.medium;

import java.util.Arrays;

public class 最长连续序列 {
    public static void main(String[] args) {
        int i = new Solution128().longestConsecutive(new int[]{9,1,4,7,3,-1,0,5,8,-1,6});
        System.out.println(i);
    }
}

class Solution128 {
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        Arrays.sort(nums);
        int index = 0;
        int ans = 1;
        int max = 1;
        while (index < nums.length - 1) {
            if (nums[index] - nums[index + 1] == -1) {
                ans++;
            } else if (nums[index] - nums[index + 1] < -1) {
                max = Math.max(max, ans);
                ans = 1;
            }
            index++;
        }
        max = Math.max(max, ans);
        return max;
    }
}