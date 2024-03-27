package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 区域和检索_数组不可变 {
    public static void main(String[] args) {

    }
}

class NumArray {

    private int[] arr;

    public NumArray(int[] nums) {
        arr = nums;
    }

    public int sumRange(int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            res += arr[i];
        }
        return res;
    }
}