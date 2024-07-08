package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class 交替子数组计数 {
    public static void main(String[] args) {
        long l = new Solution3101().countAlternatingSubarrays(new int[]{0, 1, 1, 1});
        System.out.println(l);
    }
}

class Solution3101 {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0L;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] != nums[i - 1]) cur++;
            else cur = 1;
            ans += cur;
        }
        return ans;
    }
}