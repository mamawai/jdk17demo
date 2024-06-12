package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 相同分数的最大操作数目1 {
    public static void main(String[] args) {
        int i = new Solution3038().maxOperations(new int[]{1,1,1,1,1,1});

    }
}

class Solution3038 {
    public int maxOperations(int[] nums) {
        int point = -1;
        for (int i = 0, j = 1; i < nums.length; i += 2, j += 2) {
            if (point < 0) point = nums[i] + nums[j];
            else if (j >= nums.length || point != nums[i] + nums[j]) return (j - 1) / 2;
        }
        return nums.length / 2;
    }
}