package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 所有数对中数位不同之和 {
    public static void main(String[] args) {

    }
}

class Solution3153 {
    public long sumDigitDifferences(int[] nums) {
        long res = 0;
        int length = 0;
        int num = nums[0];
        // 计算位数
        while (num != 0) {
            num /= 10;
            length++;
        }
        // 构建数位数组
        int[][] cnt = new int[length][10];
        for (int n : nums) {
            int index = 0;
            while (n != 0) {
                cnt[index][n % 10]++;
                n /= 10;
                index++;
            }
        }
        // 计算总和
        for (int[] digit : cnt) {
            for (int i = 0; i < digit.length; i++) {
                int d = digit[i];
                for (int j = i + 1; j < digit.length; j++) {
                    res += (long) d * digit[j];
                }
            }
        }
        return res;
    }
}