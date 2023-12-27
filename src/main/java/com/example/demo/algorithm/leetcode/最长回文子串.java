package com.example.demo.algorithm.leetcode;

import java.util.LinkedList;

public class 最长回文子串 {
    public static void main(String[] args) {
        String s = new Solution5().longestPalindrome("babad");
        System.out.println(s);
    }
}

/**
 * 没做出来
 */
class Solution5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = findPalindrome(i, i, s);
            int len2 = findPalindrome(i, i+1, s);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end+1);
    }

    private int findPalindrome(int left, int right, String s) {
        while (left >= 0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}