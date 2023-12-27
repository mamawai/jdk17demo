package com.example.demo.algorithm.leetcode;

import java.util.Arrays;

public class 搜索旋转排序数组 {
    public static void main(String[] args) {
        int search = new Solution33().search(new int[]{3, 4, 5, 6, 7, 8, 0, 1, 2}, 1);
        System.out.println(search);
    }
}

/**
 * 要求时间复杂度logN
 */
class Solution33 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = n -1;
        while (l <= r) {
            int mid = (l+r) / 2;
            // 跳出while条件
            if (nums[mid] == target) {
                return mid;
            }

            // l 到 mid单调
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}