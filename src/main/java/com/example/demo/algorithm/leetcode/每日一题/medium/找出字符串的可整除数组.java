package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 找出字符串的可整除数组 {
    public static void main(String[] args) {

    }
}

class Solution2575 {
    public int[] divisibilityArray(String word, int m) {
        int[] ans = new int[word.length()];

        char[] chars = word.toCharArray();
        long x = 0;
        for (int i = 0; i < chars.length; i++) {
            char w = chars[i];
            x = (x * 10 + (w - '0')) % m;
            ans[i] = m == 0 ? 1 : 0;
        }
        return ans;
    }
}
