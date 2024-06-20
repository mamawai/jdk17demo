package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 数组的最大美丽值 {
    public static void main(String[] args) {
        int i = new Solution2779().maximumBeauty(new int[]{4,6,1,2}, 2);
        System.out.println(i);
        // 0 1 1 0 1 0 1 0 0 0 0
    }
}

class Solution2779 {
    public int maximumBeauty(int[] nums, int k) {
        // total为nums元素个数由于arr定义的很大当total为0需要跳出
        int total = nums.length;
        int[] arr = new int[100001];
        for (int num : nums) {
            arr[num]++;
        }
        int l = 0;
        int r = 2 * k;
        int windows = 0;
        // 先算窗口内的值
        for (int i = l; i <= (Math.min(r, arr.length)) ; i++) {
            total -= arr[i];
            windows += arr[i];
            if (total == 0) break;
        }
        int max = windows;
        // 滑动窗口
        while (++r < arr.length && total > 0) {
            if (arr[r] > 0 || arr[l] > 0) {
                windows = windows + arr[r] - arr[l];
                max = Math.max(max, windows);
                total -= arr[r];
            }
            l++;
        }
        return max;
    }
}