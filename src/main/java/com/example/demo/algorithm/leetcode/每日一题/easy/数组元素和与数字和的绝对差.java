package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 数组元素和与数字和的绝对差 {
    public static void main(String[] args) {

    }
}

class Solution2535 {
    public int differenceOfSum(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans += num;
            while (num != 0) {
                ans -= num % 10;
                num /= 10;
            }
        }
        return Math.abs(ans);
    }
}