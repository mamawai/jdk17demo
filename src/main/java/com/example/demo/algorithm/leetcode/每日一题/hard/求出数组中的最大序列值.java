package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 求出数组中的最大序列值 {
    public static void main(String[] args) {
        int i = new Solution3287().maxValue(new int[]{1, 2, 4, 5, 6, 7, 8, 9, 10}, 2);
        System.out.println(i);
    }
}

class Solution3287 {
    public int maxValue(int[] nums, int k) {
        List<Set<Integer>> A = findORs(nums, k);
        List<Set<Integer>> B = findORs(reverse(nums), k);
        int mx = 0;
        for (int i = k - 1; i < nums.length - k; i++) {
            for (int a : A.get(i)) {
                for (int b : B.get(nums.length - i - 2)) {
                    mx = Math.max(mx, a ^ b);
                }
            }
        }
        return mx;
    }

    private List<Set<Integer>> findORs(int[] nums, int k) {
        List<Set<Integer>> dp = new ArrayList<>();
        List<Set<Integer>> prev = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            prev.add(new HashSet<>());
        }
        prev.get(0).add(0);
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.min(k - 1, i + 1); j >= 0; j--) {
                for (int x : prev.get(j)) {
                    prev.get(j + 1).add(x | nums[i]);
                }
            }
            dp.add(new HashSet<>(prev.get(k)));
        }
        return dp;
    }

    private int[] reverse(int[] nums) {
        int[] reversed = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            reversed[i] = nums[nums.length - 1 - i];
        }
        return reversed;
    }
}