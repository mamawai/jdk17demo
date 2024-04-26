package com.example.demo.algorithm.leetcode.每日一题.hard;

public class 缺失的第一个正数 {
    public static void main(String[] args) {

    }
}

/**
 * O(N) 原地哈希
 */
class Solution41 {
    public int firstMissingPositive(int[] nums) {
        // 遍历nums将每一个大于0 且数值小于等于nums.length 且没有放在nums[i] - 1位置上的数字进行交换

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}