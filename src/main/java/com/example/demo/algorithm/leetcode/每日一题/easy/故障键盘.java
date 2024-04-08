package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 故障键盘 {
    public static void main(String[] args) {

    }
}

class Solution2810 {
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c != 'i') sb.append(c);
            else sb.reverse();
        }
        return sb.toString();
    }
}