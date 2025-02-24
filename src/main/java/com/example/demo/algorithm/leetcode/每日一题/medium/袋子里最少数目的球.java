package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 袋子里最少数目的球 {
    public static void main(String[] args) {
        int i = new Solution1760().minimumSize(new int[]{2, 4, 8, 2}, 4);
        System.out.println(i);
    }
}

class Solution1760 {
    public int minimumSize(int[] nums, int maxOperations) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int left = 0; // 循环不变量 check(left) == false
        int right = mx; // 循环不变量 check(right) == true
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(nums, maxOperations, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int maxOperations, int m) {
        long cnt = 0;
        for (int x : nums) {
            cnt += (x - 1) / m;
        }
        return cnt <= maxOperations;
    }
}