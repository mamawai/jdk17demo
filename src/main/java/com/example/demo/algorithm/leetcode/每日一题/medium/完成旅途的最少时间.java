package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 完成旅途的最少时间 {
    public static void main(String[] args) {
        long l = new Solution2187().minimumTime(new int[]{9,3,10,5}, 2);
        System.out.println(l);
    }
}

class Solution2187 {
    public long minimumTime(int[] time, int totalTrips) {
        int minT = Integer.MAX_VALUE;
        int maxT = 0;
        for (int t : time) {
            minT = Math.min(minT, t);
            maxT = Math.max(maxT, t);
        }
        long left = (long) minT  - 1;
        // 循环不变量：check(right) 恒为 true
        long right = (long) minT * totalTrips;
        // 开区间 (left, right) 不为空
        while (left + 1 < right) {
            long mid = (left + right) >>> 1;
            if (check(mid, time, totalTrips)) {
                // 缩小二分区间为 (left, mid)
                right = mid;
            } else {
                // 缩小二分区间为 (mid, right)
                left = mid;
            }
        }
        // 此时 left 等于 right-1
        // check(left) = false 且 check(right) = true，所以答案是 right
        return right; // 最小的 true
    }

    private boolean check(long x, int[] time, int totalTrips) {
        long sum = 0;
        for (int t : time) {
            sum += x / t;
            if (sum >= totalTrips) {
                return true;
            }
        }
        return false;
    }
}