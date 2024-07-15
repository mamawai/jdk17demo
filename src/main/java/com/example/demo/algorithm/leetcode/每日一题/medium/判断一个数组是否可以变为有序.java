package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 判断一个数组是否可以变为有序 {
    public static void main(String[] args) {
        boolean b = new Solution3011().canSortArray(new int[]{2,3,8,4});
        System.out.println(b);
    }
}

class Solution3011 {
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, Integer.bitCount(num));
        }
        int[] copied = Arrays.copyOf(nums, n);
        Arrays.sort(nums);
        if (Arrays.equals(nums, copied)) return true;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Integer bc = map.get(copied[i]);
            if (bc == map.get(nums[i])) {
                if (i > 0) {
                    if (map.get(copied[i - 1]) != bc) {
                        if (!set.add(bc)) return false;
                    }
                } else set.add(bc);
            } else {
                return false;
            }
        }
        return true;
    }
    // 16 2 4 9 8
    // 2 4 8 9 16
}