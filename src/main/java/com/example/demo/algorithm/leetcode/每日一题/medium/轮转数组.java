package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 轮转数组 {
    public static void main(String[] args) {
        Solution189 solution189 = new Solution189();
        solution189.rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }
}

class Solution189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] tail = new int[k];
        int[] head = new int[n - k];
        for (int i = n - k; i < nums.length; i++) {
            tail[i - n + k] = nums[i];
        }
        for (int i = 0; i < n - k; i++) {
            head[i] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                nums[i] = tail[i];
            } else {
                nums[i] = head[i - k];
            }
        }
    }
}