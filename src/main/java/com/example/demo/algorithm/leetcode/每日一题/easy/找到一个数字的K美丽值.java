package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 找到一个数字的K美丽值 {
    public static void main(String[] args) {
        int i = new Solution2269().divisorSubstrings(10, 2);
        System.out.println(i);
    }
}

class Solution2269 {
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n - k + 1; i++) {
            int v = (int) (num % Math.pow(10, k + i));
            v /= (int) Math.pow(10, i);
            if (v != 0 && num % v == 0) ans ++;
        }
        return ans;
    }
}