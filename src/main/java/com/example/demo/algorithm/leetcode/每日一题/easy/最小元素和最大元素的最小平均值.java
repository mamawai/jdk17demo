package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class 最小元素和最大元素的最小平均值 {
    public static void main(String[] args) {

    }
}

class Solution3194 {
    public double minimumAverage(int[] nums) {
        double min = Double.MAX_VALUE;
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        while (l < r) min = Math.min(min, (nums[l++] + nums[r--]) / 2.0);
        return min;
    }
}
