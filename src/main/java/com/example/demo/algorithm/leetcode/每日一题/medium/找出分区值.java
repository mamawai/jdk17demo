package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.Map;

public class 找出分区值 {
    public static void main(String[] args) {

    }
}

class Solution2740 {
    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
}