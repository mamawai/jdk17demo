package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 计数质数 {
    public static void main(String[] args) {
        int i = new Solution204().countPrimes(20);
    }
}

class Solution204 {
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}


class Solution204B {
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for(int i = 2; i < n; i++) {
            if(isPrime[i] == 1) {
                ans++;
                if ((long) i * i < n) {
                    for (int k = i * i; k < n; k += i) {
                        isPrime[k] = 0;
                    }
                }
            }
        }
        return ans;
    }
}