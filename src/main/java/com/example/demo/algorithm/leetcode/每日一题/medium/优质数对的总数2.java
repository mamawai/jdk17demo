package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 优质数对的总数2 {
    public static void main(String[] args) {
        long l = new Solution3164().numberOfPairs(
                new int[]{70,50},
                new int[]{5,7}, 10
        );
    }
}

class Solution3164 {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> count2 = new HashMap<>();
        int max1 = 0;
        for (int num : nums1) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            max1 = Math.max(max1, num);
        }
        for (int num : nums2) {
            count2.put(num, count2.getOrDefault(num, 0) + 1);
        }
        long res = 0;
        for (int a : count2.keySet()) {
            for (int b = a * k; b <= max1; b += a * k) {
                if (count.containsKey(b)) {
                    res += (long) count.get(b) * count2.get(a);
                }
            }
        }
        return res;
    }

}
