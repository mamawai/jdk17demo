package com.example.demo.algorithm.leetcode.每日一题;

public class 字典序最小回文串 {
    public static void main(String[] args) {
        String s = new Solution2697().makeSmallestPalindrome("egcfe");
        System.out.println(s);
    }
}

class Solution2697 {
    public String makeSmallestPalindrome(String s) {
        int length = s.length();
        int l = 0;
        int r = length - 1;
        char[] charArray = s.toCharArray();
        while (l < r) {
            if (charArray[l] > charArray[r]) {
                charArray[l] = charArray[r];
            } else if (charArray[l] < charArray[r]){
                charArray[r] = charArray[l];
            }
            l++;
            r--;
        }
        return String.valueOf(charArray);
    }
}