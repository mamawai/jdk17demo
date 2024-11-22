package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 使二进制数组全部等于1的最少操作次数1 {
    public static void main(String[] args) {

    }
}

class Solution3191 {
    public int minOperations(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                int loops = 0;
                for (int j = i + 1; j < Math.min(i + 3, nums.length); j++) {
                    loops++;
                    nums[j] ^= 1;
                }
                if (loops == 2) cnt++;
                else return -1;
            }
        }
        return cnt;
    }
}