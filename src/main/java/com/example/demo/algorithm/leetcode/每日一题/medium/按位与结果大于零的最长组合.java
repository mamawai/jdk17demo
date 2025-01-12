package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 按位与结果大于零的最长组合 {
    public static void main(String[] args) {

    }
}

class Solution2275 {
    public int largestCombination(int[] candidates) {
        int[] bitSet = new int[24];
        for (int candidate : candidates) {
            int i = 0;
            while (candidate > 0) {
                bitSet[i++] += candidate & 1;
                candidate >>= 1;
            }
        }
        int ans = 0;
        for (int j : bitSet) ans = Math.max(ans, j);
        return ans;
    }
}