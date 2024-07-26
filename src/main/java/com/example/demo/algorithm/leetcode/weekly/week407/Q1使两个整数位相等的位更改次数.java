package com.example.demo.algorithm.leetcode.weekly.week407;

public class Q1使两个整数位相等的位更改次数 {
    public static void main(String[] args) {
        int i = new Solution407q1().minChanges(13, 10);
        System.out.println(i);
    }
}

/**
 * 时间：19min
 */
class Solution407q1 {
    public int minChanges(int n, int k) {
        // 1101 -> 0100  1001
        // 1101 -> 1010
        // 0111
        if (n < k) return -1;
        if (n == k) return 0;
        int i = n ^ k;
        if ((n & i) != i) return -1;
        return Integer.bitCount(i);
    }
}
