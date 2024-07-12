package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 统计移除递增子数组的数目2 {
    public static void main(String[] args) {
        long l = new Solution2972().incremovableSubarrayCount(new int[]{5, 6, 3, 4, 5, 7});
        System.out.println(l);
    }
}

class Solution2972 {
    public long incremovableSubarrayCount(int[] nums) {
        int i = 0;
        long res;
        int n = nums.length;
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i == n - 1) { // 每个非空子数组都可以移除
            return (long) n * (n + 1) / 2;
        }
        // 5 6 3 4 5 7 ->
        res = i + 2;// 5\6\56
        for (int j = n - 1; j == n - 1 || nums[j] < nums[j + 1]; j--) {
            while (i >= 0 && nums[i] >= nums[j]) {
                i--;
            }
            res += i+2;// 567/67/57
        }
        return res;
    }
}