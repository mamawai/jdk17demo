package com.example.demo.algorithm.leetcode.weekly.week407;

public class 字符串元音游戏 {
    public static void main(String[] args) {

    }
}
// 11min
class Solution407q2 {
    public boolean doesAliceWin(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c=='a'||c =='e'||c=='i'||c=='o'||c=='u') return true;
        }
        return false;
    }
}