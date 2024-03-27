package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 数组元素的最小非零乘积 {
    public static void main(String[] args) {
        int i = new Solution1969().minNonZeroProduct(4);
        System.out.println(i);
    }
}

class Solution1969 {
    private static final int MOD = 1_000_000_007;

    private long pow(long x, int p) {
        x %= MOD;
        long res = 1;
        while (p-- > 0) {
            res = res * x % MOD;
            x = x * x % MOD;
        }
        return res;
    }

    public int minNonZeroProduct(int p) {
        long k = (1L << p) - 1;
        return (int) (k % MOD * pow(k - 1, p - 1) % MOD);
    }
}