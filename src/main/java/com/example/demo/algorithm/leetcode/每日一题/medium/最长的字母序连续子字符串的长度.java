package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 最长的字母序连续子字符串的长度 {
    public static void main(String[] args) {
        int i = new Solution2414().longestContinuousSubstring("abcde");
        System.out.println(i);
    }
}

class Solution2414 {
    public int longestContinuousSubstring(String s) {
        int n = s.length();
        int cnt = 1;
        int ans = 1;
        char pre = s.charAt(0);
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c - pre == 1) cnt++;
            else {
                ans = Math.max(ans, cnt);
                cnt = 1;
            }
            pre = c;
        }
        return Math.max(ans, cnt);
    }
}