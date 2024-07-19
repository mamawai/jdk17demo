package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 得到更多分数的最少关卡数目 {
    public static void main(String[] args) {
        int i = new Solution3096().minimumLevels(new int[]{1, 1});
        System.out.println(i);
    }
}

class Solution3096 {
    public int minimumLevels(int[] possible) {
        int[] added = new int[possible.length];
        int sum = 0;
        for (int i = 0; i < possible.length; i++) {
            int p = possible[i];
            if (p == 0) added[i] = --sum;
            else added[i] = ++sum;
        }
        for (int i = 0; i < added.length - 1; i++) {
            int add = added[i];
            if (sum - add < add) return i + 1;
        }
        return -1;
    }
}