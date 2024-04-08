package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 访问完所有房间的第一天 {
    public static void main(String[] args) {
        // 2 6 14 30
        int i = new Solution1997().firstDayBeenInAllRooms(new int[]{0, 1, 1, 0, 0});
        System.out.println(i);
    }
}

class Solution1997 {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long[] f = new long[n];
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + 1 + f[i - 1] - f[nextVisit[i - 1]] + 1 + mod) % mod;
        }
        return (int) f[n - 1];
    }
}