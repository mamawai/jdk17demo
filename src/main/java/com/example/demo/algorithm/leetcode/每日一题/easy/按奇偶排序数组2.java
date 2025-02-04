package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.Arrays;

public class 按奇偶排序数组2 {
    public static void main(String[] args) {
        int[] ints = new Solution922().sortArrayByParityII(new int[]{2,3});
        System.out.println(Arrays.toString(ints));
    }
}

class Solution922 {
    public int[] sortArrayByParityII(int[] nums) {
        int i = 0;
        int j = 1;
        int n = nums.length;
        while(true) {
            while (i < n && nums[i] % 2 == 0) i += 2; // 找到第一个偶数位置的奇数
            while (j < n && nums[j] % 2 != 0) j += 2; // 找到第一个奇数位置的偶数
            if (!(i < n && j < n)) break;
            int tmp = nums[i]; // 交换位置
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}