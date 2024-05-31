package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 找出缺失和重复的数字 {
    public static void main(String[] args) {

    }
}

class Solution2965 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int dup = -1;
        int totalSum = (n * n + 1) * (n * n) / 2;
        int[] nums = new int[n * n + 1];
        for (int[] row : grid) {
            for (int num : row) {
                if (nums[num] == 1) dup = num;
                nums[num]++;
                totalSum -= num;
            }
        }
        return new int[]{dup, dup + totalSum};
    }
}
