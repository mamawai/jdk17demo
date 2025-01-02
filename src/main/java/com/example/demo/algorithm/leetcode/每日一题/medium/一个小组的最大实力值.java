package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 一个小组的最大实力值 {
    public static void main(String[] args) {

    }
}

class Solution2708 {
    public long maxStrength(int[] nums) {
        if (nums.length == 1) return nums[0];
        long res = 1L;
        // 先排序
        Arrays.sort(nums);
        // 全大于0 直接返回
        if (nums[0] > 0) {
            for (int num : nums) res *= num;
            return res;
        } else {
            // 有小于等于0的情况
            int i = 0;
            int negCnt = 0;
            while (i < nums.length && nums[i] < 0) {
                negCnt++;
                res *= nums[i++];
            }
            // 回退
            if ((negCnt & 1) != 0) res /= nums[i - 1];
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > 0) res *= nums[j];
            }
            // 特殊情况[-2,0]
            if (negCnt <= 1 && nums[nums.length - 1] == 0) return 0;
        }
        return res;
    }
}