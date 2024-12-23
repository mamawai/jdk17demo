package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 统计不是特殊数字的数字数量 {
    public static void main(String[] args) {
        int i = new Solution3233().nonSpecialCount(4, 16);
        System.out.println(i);
    }
}

class Solution3233 {
    public int nonSpecialCount(int l, int r) {
        // 先计算上下界的平方根
        double sqrt = Math.sqrt(l);
        int sqrtL = sqrt > (int) sqrt ? (int) sqrt + 1 : (int) sqrt;
        int sqrtR = (int) Math.sqrt(r);
        if (sqrtL > sqrtR) return r - l + 1;
        // 然后找sqrtL和sqrtR之间的质数
        return r - l + 1 - countPrimes(sqrtR) + countPrimes(sqrtL - 1);
    }

    public int countPrimes(int n) {
        int[] isPrime = new int[n + 1];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i <= n) {
                    for (int j = i * i; j <= n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}