package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 字母在字符串中的百分比 {
    public static void main(String[] args) {

    }
}

class Solution2278 {
    public int percentageLetter(String s, char letter) {
        int length = s.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }
        return count * 100 / length;
    }
}