package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;
import java.util.Map;

public class 构成整天的下标对数目2 {
    public static void main(String[] args) {
        long l = new Solution3185().countCompleteDayPairs(new int[]{12, 12, 30, 24, 24});
        System.out.println(l);
    }
}

class Solution3185 {
    public long countCompleteDayPairs(int[] hours) {
        // map 可以换成24大小的int数组
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int hour : hours) {
            map.put(hour % 24, map.getOrDefault(hour % 24, 0) + 1);
        }
        for (int i = 0; i <= 12; i++) {
            if (i == 0 || i == 12) {
                // 自己跟自己配对
                ans += (long) map.getOrDefault(i, 0) * (map.getOrDefault(i, 0) - 1) / 2;
            } else {
                ans += (long) map.getOrDefault(i, 0) * map.getOrDefault(24 - i, 0);
            }
        }
        return ans;
    }
}