package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 找出美丽数组的最小和 {
    public static void main(String[] args) {
        int i = new Solution2834().minimumPossibleSum(1000000000, 1000000000);
        System.out.println(i);
    }
}

class Solution2834 {
    public int minimumPossibleSum(int n, int target) {
        int count = target / 2 ;
        if (n <= count) {
            return ((1 + n) * n ) / 2;
        } else {
            long biggerTimes = n - count;
            long i = (((long) target + (long) target + (biggerTimes - 1)) * biggerTimes) / 2;
            long j = (1 + (n - biggerTimes)) * (n - biggerTimes) / 2;
            long l = (j + i) % 1000000007L;
            return (int) l;
        }
    }
}