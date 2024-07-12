package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.lang.reflect.Array;
import java.util.Arrays;

public class 最小数字游戏 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
        }
        return nums;
    }
}