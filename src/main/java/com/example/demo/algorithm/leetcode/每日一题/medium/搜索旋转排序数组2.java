package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 搜索旋转排序数组2 {
    public static void main(String[] args) {
        boolean search = new Solution81().search(new int[]{0}, 1);
        System.out.println(search);
    }
}

class Solution81 {
    public boolean search(int[] nums, int target) {
        if (nums[0] == target) return true;
        boolean forward = nums[0] < target;
        int n = nums.length;
        if (forward) {
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] == target) return true;
                if (nums[i + 1] < nums[i]) return false;
                if (nums[i] < target && target < nums[i + 1]) return false;
            }
        } else {
            for (int i = n - 1; i >= 1; i--) {
                if (nums[i] == target) return true;
                if (nums[i - 1] > nums[i]) return false;
                if (nums[i - 1] > target && target > nums[i]) return false;
            }
        }
        return nums[n - 1] == target;
    }
}