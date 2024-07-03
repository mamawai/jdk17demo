package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 执行子串操作后的字典最小字符串 {
    public static void main(String[] args) {
        String s = new Solution2734().smallestString("aaa");
        System.out.println(s);
    }
}

class Solution2734 {
    public String smallestString(String s) {
        int n = s.length();
        char[] sCharArray = s.toCharArray();
        int i = 0;
        while (i < n && s.charAt(i) == 'a') {
            i++;
        }
        if (i == n) {
            sCharArray[n - 1] = 'z';
            return new String(sCharArray);
        }
        while (i < n && s.charAt(i) != 'a') {
            sCharArray[i++]--;
        }
        return new String(sCharArray);
    }
}