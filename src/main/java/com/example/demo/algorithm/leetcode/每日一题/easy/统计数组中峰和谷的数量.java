package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 统计数组中峰和谷的数量 {
    public static void main(String[] args) {

    }
}

class Solution2210 {
    public int countHillValley(int[] nums) {
        int ans = 0;
        int isLastPositive = 0;
        if (nums[1] - nums[0] > 0) {
            isLastPositive = 1;
        } else if (nums[1] - nums[0] < 0) {
            isLastPositive = - 1;
        }
        for (int i = 1, j = 2; j < nums.length; i++, j++) {
            int isNowPositive = 0;
            if (nums[j] - nums[i] > 0) {
                isNowPositive = 1;
            } else if (nums[j] - nums[i] < 0) {
                isNowPositive = -1;
            }
            if (isLastPositive == -1 && isNowPositive == 1) {
                ans++;
                isLastPositive = 1;
            } else if (isLastPositive == 1 && isNowPositive == -1) {
                ans++;
                isLastPositive = -1;
            } else if (isLastPositive == 0 && isNowPositive == 1) {
                isLastPositive = 1;
            } else if (isLastPositive == 0 && isNowPositive == -1) {
                isLastPositive = -1;
            }
        }
        return ans;
    }
}