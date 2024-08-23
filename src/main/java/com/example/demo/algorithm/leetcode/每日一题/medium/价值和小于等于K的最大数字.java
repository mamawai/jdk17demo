package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.BitSet;

public class 价值和小于等于K的最大数字 {
    public static void main(String[] args) {

    }
}

class Solution3007 {
    public long findMaximumNumber(long k, int x) {
        // 开区间二分，原理见 https://www.bilibili.com/video/BV1AP41137w7/
        long left = 0;
        long right = (k + 1) << x;
        while (left + 1 < right) {
            long mid = (left + right) >>> 1;
            if (countDigitOne(mid, x) <= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private long countDigitOne(long num, int x) {
        long res = 0;
        int i = x - 1;
        for (long n = num >> i; n > 0; n >>= x, i += x) {
            res += (n / 2) << i;
            if (n % 2 > 0) {
                long mask = (1L << i) - 1;
                res += (num & mask) + 1;
            }
        }
        return res;
    }
}