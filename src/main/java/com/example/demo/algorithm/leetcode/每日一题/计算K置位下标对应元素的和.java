package com.example.demo.algorithm.leetcode.每日一题;

import java.util.Arrays;
import java.util.List;

public class 计算K置位下标对应元素的和 {
    public static void main(String[] args) {
        int i = new Solution2859().sumIndicesWithKSetBits(Arrays.asList(5, 10, 1, 5, 2), 1);
        System.out.println(i);
    }
}

class Solution2859 {
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            int integer = nums.get(i);
            if (Integer.bitCount(i) == k) {
                ans += integer;
            }
        }
        return ans;
    }
}