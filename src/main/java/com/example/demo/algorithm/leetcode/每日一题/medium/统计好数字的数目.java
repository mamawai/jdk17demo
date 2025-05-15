package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 统计好数字的数目 {
    public static void main(String[] args) {

    }
}

class Solution1922 {

    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        return (int) (pow(5, (n + 1) / 2) * pow(4, n / 2) % MOD);
    }

    private long pow(long x, long n) {
        // 比如你要算13次幂 13 -> 1101 8 + 4 + 1 = 13次幂
        long res = 1;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
}